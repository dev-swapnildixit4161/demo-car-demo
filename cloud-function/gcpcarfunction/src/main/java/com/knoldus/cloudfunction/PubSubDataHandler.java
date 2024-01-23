package com.knoldus.cloudfunction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.functions.CloudEventsFunction;
import com.google.events.cloud.pubsub.v1.Message;
import com.google.events.cloud.pubsub.v1.MessagePublishedData;
import com.knoldus.cloudfunction.exception.ConfigurationLoadException;
import com.knoldus.cloudfunction.model.Vehicle;
import io.cloudevents.CloudEvent;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles CloudEvents containing Pub/Sub data.
 */
public class PubSubDataHandler implements CloudEventsFunction {
  private static final Logger logger = Logger.getLogger(
          PubSubDataHandler.class.getName());

  protected static final double MILEAGE_CONVERSION_RATE = 1.609344;
  /**
   * The Firestore instance for
   * interacting with the Firestore database.
   */
  private static Firestore firestore;

  /**
   * Constructor for the PubSubDataHandler class.
   * Initializes the Firestore instance.
   */
  public PubSubDataHandler(Firestore firestore) {
    PubSubDataHandler.firestore = firestore;
  }

  protected Properties loadConfigProperties() throws ConfigurationLoadException {
    Properties properties = new Properties();
    try (InputStream input = getClass().getResourceAsStream("/config.properties")) {
      properties.load(input);
    } catch (IOException e) {
      throw new ConfigurationLoadException("Failed to load configuration properties", e);
    }
    return properties;
  }

  /**
   * Processes the incoming CloudEvent containing Pub/Sub data.
   *
   * @param event The incoming CloudEvent.
   * @throws JsonProcessingException
   * If there is an error parsing the JSON data.
   */
  @Override
  public void accept(final CloudEvent event) throws IOException, ConfigurationLoadException {
    String cloudEventData = new String(event.getData().toBytes());
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature
            .FAIL_ON_UNKNOWN_PROPERTIES, false);
    MessagePublishedData data = objectMapper.readValue(cloudEventData, MessagePublishedData.class);
    Message message = data.getMessage();
    String encodedData = message.getData();
    String decodedData = new String(Base64.getDecoder().decode(encodedData));

    Vehicle vehicleData = objectMapper.readValue(decodedData, Vehicle.class);

    double conversionRate = fetchConversionRateFromAPI("INR");

    double priceInRupees = transformPrice(vehicleData.getPrice(), conversionRate);
    double mileageInKmpl = transformMileage(vehicleData
            .getMileage());

    vehicleData.setPrice(priceInRupees);
    vehicleData.setMileage(mileageInKmpl);

    logger.log(Level.INFO, () -> "Mileage in kmpl: " + vehicleData
            .getMileage());
    logger.log(Level.INFO, () -> "Price in rupees: " + vehicleData
            .getPrice());
    saveDataToFirestore(vehicleData);
  }

  protected double fetchConversionRateFromAPI(String toCurrency) throws ConfigurationLoadException {
    Properties properties = loadConfigProperties();
    String currencyApiKey = properties.getProperty("currency.api.key");
    String currencyApiUrl = properties.getProperty("currency.api.url");
    try {
      String apiUrl = currencyApiUrl + currencyApiKey;
      URL url = new URL(apiUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }

        String jsonResponse = response.toString();
        JSONObject json = new JSONObject(jsonResponse);
        JSONObject data = json.getJSONObject("data");

        if (data.has(toCurrency)) {
          JSONObject currencyEntry = data.getJSONObject(toCurrency);
          return currencyEntry.getDouble("value");
        } else {
          logger.log(Level.SEVERE,() -> "Currency not found: " + toCurrency);
          return 0.0;
        }
      } else {
        logger.log(Level.SEVERE,() -> "HTTP Request failed with response code: " + responseCode);
      }
    } catch (IOException ioException) {
      logger.log(Level.SEVERE, () -> "Malformed URL: " + ioException.getMessage());
    }
    return 0.0;
  }

  /**
   * Converts the price from dollars to rupees.
   *
   * @param priceInDollars The price in dollars.
   * @return The price in rupees.
   */
  protected double transformPrice(final double priceInDollars, final double conversionRate) {
    return priceInDollars * conversionRate;
  }
  /**
   * Converts the mileage from miles to kilometers per liter.
   *
   * @param mileageInMiles The mileage in miles.
   * @return The mileage in kilometers per liter.
   */
  protected double transformMileage(final double mileageInMiles) {
    return mileageInMiles * MILEAGE_CONVERSION_RATE;
  }
  /**
   * Saves the data from the provided
   * model.Vehicle object to Firestore.
   *
   * @param vehicleData
   * The model.Vehicle object containing the data to be saved.
   */
  protected void saveDataToFirestore(Vehicle vehicleData) {
    DocumentReference docRef = firestore.collection("Car").document();
    ApiFuture<WriteResult> result = docRef.set(vehicleData);
    try {
      result.get();
      logger.log(Level.INFO, () -> "Message data saved to Firestore: " + vehicleData);
    } catch (InterruptedException e) {
      logger.log(Level.SEVERE,() -> "Thread was interrupted while saving message data to Firestore: " + e.getMessage());
      Thread.currentThread().interrupt();
    } catch (ExecutionException e) {
      logger.log(Level.SEVERE,() -> "Error executing task for saving message data to Firestore: " + e.getMessage());
    }
  }
}
