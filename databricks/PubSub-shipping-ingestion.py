# Databricks notebook source
# DBTITLE 1,Authentication Credential to read events from PubSub

client_id_secret = dbutils.secrets.get(scope = "gcp-pubsub", key = "client_id_1")
client_email_secret = dbutils.secrets.get(scope = "gcp-pubsub", key = "client_email_1")
private_key_secret = dbutils.secrets.get(scope = "gcp-pubsub", key = "private_key_1")
private_key_id_secret = dbutils.secrets.get(scope = "gcp-pubsub", key = "private_key_id_1")
authOptions = {"client_id": client_id_secret,
               "client_email": client_email_secret,
               "private_key": private_key_secret,
               "private_key_id": private_key_id_secret}

# COMMAND ----------

# DBTITLE 1, Spark structured streaming ingestion from PubSub topic
from pyspark.sql.functions import *
from pyspark.sql.types import *
from pyspark.sql import *

shipingInputDF = spark.readStream.format("pubsub") \
.option("subscriptionId", "shipment_subscription") \
.option("topicId", "shipping-notification") \
.option("projectId", "datamesh-2") \
.option("numFetchPartitions", "3") \
.options(**authOptions) \
.load() 

# COMMAND ----------

# DBTITLE 1,Schema for the shipping events
shipingDetailsSchema = (
                 StructType()
                    .add("shipmentId", "string")
                    .add("orderId", "string")
                    .add("paymentId", "string")
                    .add("userId", "string")
                    .add("firstName", "string")
                    .add("lastName", "string")
                    .add("address", "string")                    
                    .add("emailId", "string")
                    .add("mobileNumber", "string")
                    .add("productId", "string")
                    .add("brand", "string")
                    .add("quantity", "integer")
                    .add("basePrice", "float")
                    .add("subTotal", "float")
                    .add("total", "float")
                    .add("tax", "float")
                    .add("totalTax", "float")
             )

# COMMAND ----------

# DBTITLE 1,Spark data frame for the input shipping event
shipingDetailDF = (
                  shipingInputDF
                      .select(
                                from_json(
                                              col("payload").cast("string"),
                                              shipingDetailsSchema
                                         )
                                  .alias("shipingdata")
                             )
                      .select(
                                "shipingdata.shipmentId",
                                "shipingdata.orderId",
                                "shipingdata.paymentId",
                                "shipingdata.userId",
                                "shipingdata.firstName",
                                "shipingdata.lastName",
                                "shipingdata.address",
                                "shipingdata.emailId",
                                "shipingdata.mobileNumber",
                                "shipingdata.productId",
                                "shipingdata.brand",
                                "shipingdata.quantity",
                                "shipingdata.basePrice",
                                "shipingdata.subTotal",
                                "shipingdata.total",
                                "shipingdata.tax",
                                "shipingdata.totalTax"
                             )
          )

# COMMAND ----------

# DBTITLE 1,Writing streaming raw shipping data frame to the delta lake table (Bronze table)
shipingDetailDF.writeStream.format("delta") \
.outputMode("append") \
.partitionBy("brand") \
.option("checkpointLocation", "/dbfs/pubsub-shippment-checkpoint-38/") \
.trigger(processingTime = '3 seconds') \
.table("main.car_demo_data_lake.shipping_bronze")

# COMMAND ----------

# DBTITLE 1,Reading streaming shippment events from bronze table
silverDF = spark.readStream.table("main.car_demo_data_lake.shipping_bronze")

# COMMAND ----------

# DBTITLE 1,Creating encryption key
from cryptography.fernet import Fernet 

encryptionKey = Fernet.generate_key()

# COMMAND ----------

# DBTITLE 1,Create Spark UDFs in python for encrypting a value
def encrypt_val(clear_text,MASTER_KEY):
    from cryptography.fernet import Fernet
    f = Fernet(MASTER_KEY)
    clear_text_b=bytes(clear_text, 'utf-8')
    cipher_text = f.encrypt(clear_text_b)
    cipher_text = str(cipher_text.decode('ascii'))
    return cipher_text

# COMMAND ----------

# DBTITLE 1,Use the UDF in a dataframe to encrypt a productid column
from pyspark.sql.functions import udf, lit, md5
from pyspark.sql.types import StringType

encrypt = udf(encrypt_val, StringType())

encryptedDF = silverDF.withColumn("userId", encrypt("userId",lit(encryptionKey))) \
                      .withColumn("firstName", encrypt("firstName",lit(encryptionKey))) \
                      .withColumn("lastName", encrypt("lastName",lit(encryptionKey))) \
                      .withColumn("address", encrypt("address",lit(encryptionKey))) \
                      .withColumn("emailId", encrypt("emailId",lit(encryptionKey))) \
                      .withColumn("mobileNumber", encrypt("mobileNumber",lit(encryptionKey)))

# COMMAND ----------

# DBTITLE 1,Writing transformed silver data frame to the silver table
encryptedDF.writeStream.format("delta") \
.outputMode("append") \
.option("checkpointLocation", "/dbfs/pubsub-shippment-sliver-checkpoint-38/") \
.partitionBy("brand") \
.trigger(processingTime = '2 seconds') \
.table("main.car_demo_data_lake.shipping_sliver")

# COMMAND ----------

# DBTITLE 1,Reading streaming data frame from silver table
goldDF = spark.readStream \
.table("main.car_demo_data_lake.shipping_sliver") 

# COMMAND ----------

# DBTITLE 1,Aggregate cars quantity and price for each brands and years
group_cols = ["brand"]
vechileGoldDF = goldDF.groupBy(group_cols) \
.agg(sum("quantity").alias("total_quantity_shipped"), sum("subTotal").alias("total_selling_price_inr"))

# COMMAND ----------

# DBTITLE 1,Writing aggregated result to Gold table
(
    vechileGoldDF.writeStream \
    .format("delta") \
    .outputMode("complete") \
    .partitionBy("brand") \
    .option("checkpointLocation", "/dbfs/pubsub-shipping-gold-38/") \
    .trigger(processingTime = '1 seconds') \
    .table("main.car_demo_all_brands.shipping_data_gold")
)
