#Terraform plugin for creating random ids
resource "random_id" "instance_id" {
 byte_length = 4
}

#----------------------------------------my-sql-----------------------------
# create My SQL database instance
resource "google_sql_database_instance" "my_sql" {
  name                 = "${var.app_name}-${var.app_environment}-db-${random_id.instance_id.hex}"
  project              = var.app_project
  region               = var.gcp_region_1
  database_version     = var.db_version

  settings {
    tier = var.db_tier
    activation_policy           = var.db_activation_policy
    disk_autoresize             = var.db_disk_autoresize
    disk_size                   = var.db_disk_size
    disk_type                   = var.db_disk_type

    location_preference {
      zone = var.gcp_zone_1
    }

    ip_configuration {
      ipv4_enabled = "true"
      authorized_networks {
        value = var.db_instance_access_cidr
      }
    }
  }
  deletion_protection = "false"
}

# create order database
resource "google_sql_database" "order_db" {
  name      = var.orders_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}

# Create inventory database
resource "google_sql_database" "inventory_db" {
  name      = var.inventory_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}

# Create payment database
resource "google_sql_database" "payment_db" {
  name      = var.payment_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}

# Create shipment database
resource "google_sql_database" "shipment_db" {
  name      = var.shipment_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}
resource "google_sql_database" "cart_db" {
  name      = var.cart_db_name
  project   = var.app_project
  instance  = google_sql_database_instance.my_sql.name
}

# create user
resource "random_id" "user_password" {
  byte_length = 8
}

resource "google_sql_user" "my_sql_user" {
  name     = var.user_name
  project  = var.app_project
  instance = google_sql_database_instance.my_sql.name
  password = var.user_password == "" ? random_id.user_password.hex : var.user_password
}

#-----------------------pub sub--------------------------------
resource "google_pubsub_topic" "shipment-notification" {
  name = "shipment-notification"
  message_retention_duration = "604800s"
}

resource "google_pubsub_topic" "vehicle" {
  name = "vehicle"
  message_retention_duration = "604800s"
}

resource "google_pubsub_subscription" "inventory_subscription" {
  name  = "inventory_subscription"
  topic = google_pubsub_topic.vehicle.name

  # 20 minutes
  message_retention_duration = "1200s"
  retain_acked_messages      = true

  ack_deadline_seconds = 20
  expiration_policy {
    ttl = "300000.5s"
  }
  retry_policy {
    minimum_backoff = "10s"
  }
  enable_message_ordering    = false
}

resource "google_pubsub_subscription" "vehicle_subscription" {
  name  = "vehicle_subscription"
  topic = google_pubsub_topic.vehicle.name

  # 20 minutes
  message_retention_duration = "1200s"
  retain_acked_messages      = true

  ack_deadline_seconds = 20
  expiration_policy {
    ttl = "300000.5s"
  }
  retry_policy {
    minimum_backoff = "10s"
  }
  enable_message_ordering    = false
}

resource "google_pubsub_subscription" "shipment_subscription" {
  name  = "shipment_subscription"
  topic = google_pubsub_topic.shipment-notification.name

  # 20 minutes
  message_retention_duration = "1200s"
  retain_acked_messages      = true

  ack_deadline_seconds = 20
  expiration_policy {
    ttl = "300000.5s"
  }
  retry_policy {
    minimum_backoff = "10s"
  }
  enable_message_ordering    = false
}

#-----------------------GKE Cluster for applications----------------------------
resource "google_container_cluster" "car-demo-gke" {
  name     = "car-demo-gke"
  location = var.gcp_region_1
  ip_allocation_policy {
    cluster_ipv4_cidr_block  = ""
    services_ipv4_cidr_block = ""
  }
  enable_autopilot = true
  deletion_protection = false
}

#GKE Cluster for axon-server
resource "google_container_cluster" "axon-server-gke" {
  name     = "axon-server-gke"
  location = var.gcp_region_1
  ip_allocation_policy {
    cluster_ipv4_cidr_block  = ""
    services_ipv4_cidr_block = ""
  }
  enable_autopilot = true
  deletion_protection = false
}

resource "null_resource" "external-secret-car-demo-gke" {
  provisioner "local-exec" {
    command = "/bin/bash external-secret-gcsm.sh car-demo-gke ${var.gcp_region_1}"
  }
  depends_on = [google_container_cluster.car-demo-gke]
}

resource "null_resource" "axon-server-gke" {
  provisioner "local-exec" {
    command = "/bin/bash axon-server-deployment.sh axon-server-gke ${var.gcp_region_1}"
  }
  depends_on = [google_container_cluster.axon-server-gke]
}

#GKE Cluster for elasticsearch
/*
resource "google_container_cluster" "elasticsearch-server-gke" {
  name     = "elasticsearch-server-gke"
  location = var.gcp_region_1
  ip_allocation_policy {
    cluster_ipv4_cidr_block  = ""
    services_ipv4_cidr_block = ""
  }
  enable_autopilot = true
  deletion_protection = false
  node_config {
    machine_type = "n2-standard-2"
  }
}
resource "null_resource" "elasticsearch-server-gke" {
  provisioner "local-exec" {
    command = "/bin/bash gcp-elasticsearch-deployment.sh elasticsearch-server-gke ${var.gcp_region_1}"
  }
  depends_on = [google_container_cluster.elasticsearch-server-gke]
}
*/

#----------------------GCP firestore----------------------------
/*
resource "google_project_service" "firestore" {
  project = var.app_project
  service = "firestore.googleapis.com"
}
*/

resource "google_firestore_database" "database" {
  project     = var.app_project
  name        = "(default)"
  location_id = var.gcp_region_1
  type        = "FIRESTORE_NATIVE"
  //depends_on = [google_project_service.firestore]
}

#------------------------- Cloud function----------------------
resource "google_storage_bucket" "function_bucket" {
  name                        = "${random_id.instance_id.hex}-gcf-source" # Every bucket name must be globally unique
  location                    = var.gcp_region_1
  uniform_bucket_level_access = true
}

data "archive_file" "source" {
  type        = "zip"
  output_path = "/tmp/function-source.zip"
  source_dir  = "/home/knoldus/IdeaProjects/car-demo/cloud-function/gcpcarfunction"
  // ex. "/home/knoldus/IdeaProjects/car-demo/cloud-function/gcpcarfunction"
}

resource "google_storage_bucket_object" "zip" {
  name   = "function-source.zip"
  bucket = google_storage_bucket.function_bucket.name
  source = data.archive_file.source.output_path # Add path to the zipped function source code
  content_type = "application/zip"
}

resource "google_cloudfunctions2_function" "function-v2" {
  name        = "car_cloud_function"
  location    = var.gcp_region_1

  event_trigger {
    trigger_region = var.gcp_region_1
    event_type     = "google.cloud.pubsub.topic.v1.messagePublished"
     pubsub_topic   = google_pubsub_topic.vehicle.id
    retry_policy   = "RETRY_POLICY_RETRY"
  }

  build_config {
    runtime     = "java17"
    entry_point = "com.knoldus.cloudfunction.PubSubDataHandler" # Set the entry point
    source {
      storage_source {
        bucket = google_storage_bucket.function_bucket.name
        object = google_storage_bucket_object.zip.name
      }
    }
  }

  service_config {
    max_instance_count = 1
    available_memory   = "256M"
    timeout_seconds    = 60
  }

  depends_on            = [
    google_storage_bucket.function_bucket,  # declared in `storage.tf`
    google_storage_bucket_object.zip
  ]
}

#------------------------- secret manger----------------------
resource "google_secret_manager_secret" "car-demo-secret" {
  secret_id = "car-demo-secret"
  replication {
    auto {}
  }
  depends_on = [google_sql_user.my_sql_user]
}

resource "google_secret_manager_secret_version" "car-demo-secret-1" {

  secret      = google_secret_manager_secret.car-demo-secret.id
  secret_data = "{\"mysql-db-username\": \"${var.user_name}\", \"mysql-db-userpassword\": \"${var.user_password}\"}"
}
