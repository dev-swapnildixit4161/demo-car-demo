package com.knoldus.cloudfunction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.knoldus.cloudfunction.model.Vehicle;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Base64;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PubSubDataHandlerTest {

    @Mock
    private Firestore mockFirestore;

    @Mock
    private ApiFuture<WriteResult> mockApiFuture;

    @Mock
    private DocumentReference mockDocumentReference;

    @Mock
    private CollectionReference mockCollectionReference;

    @InjectMocks
    private PubSubDataHandler pubSubDataHandler;

    @Before
    public void setUp() throws ExecutionException, InterruptedException {
        MockitoAnnotations.openMocks(this);
        pubSubDataHandler = new PubSubDataHandler(mockFirestore);

        when(mockFirestore.collection("Car")).thenReturn(mockCollectionReference);
        when(mockCollectionReference.document()).thenReturn(mockDocumentReference);
        when(mockDocumentReference.set(any(Vehicle.class))).thenReturn(mockApiFuture);
        when(mockApiFuture.get()).thenReturn(mock(WriteResult.class));
    }

    @Test
    public void constructorTest() {
        assertNotNull(pubSubDataHandler, "PubSubDataHandler should be initialized");
    }

    @Test
    public void acceptTest() throws Exception {

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        String vehicleJson = new ObjectMapper().writeValueAsString(vehicle);

        CloudEventData cloudEventData = mock(CloudEventData.class);
        String validJson = "{\"message\": {\"data\": \"" + Base64.getEncoder().encodeToString(vehicleJson.getBytes()) + "\"}}";
        when(cloudEventData.toBytes()).thenReturn(validJson.getBytes());
        CloudEvent event = mock(CloudEvent.class);
        when(event.getData()).thenReturn(cloudEventData);

        pubSubDataHandler.accept(event);

        // Verify the interactions with Firestore
        verify(mockFirestore, atLeastOnce()).collection(anyString());
    }



    @Test
    public void transformPriceTest() {
        double priceInDollars = 10;
        double conversionRate = 74.85;
        double expectedPriceInRupees = priceInDollars * conversionRate;
        double actualPriceInRupees = pubSubDataHandler.transformPrice(priceInDollars, conversionRate);
        assertEquals(expectedPriceInRupees, actualPriceInRupees,
                "Price should be correctly transformed from dollars to rupees");
    }

    @Test
    public void transformMileageTest() {
        double mileageInMiles = 10;
        double expectedMileageInKilometers = mileageInMiles * PubSubDataHandler.MILEAGE_CONVERSION_RATE;
        double actualMileageInKilometers = pubSubDataHandler.transformMileage(mileageInMiles);
        assertEquals(expectedMileageInKilometers, actualMileageInKilometers,
                "Mileage should be correctly transformed from miles to kilometers");
    }

    @Test
    public void saveDataToFirestoreSuccessTest() throws ExecutionException, InterruptedException {
        Vehicle vehicleData = mock(Vehicle.class);

        when(mockApiFuture.get()).thenReturn(mock(WriteResult.class)); // Simulate successful Firestore operation

        assertDoesNotThrow(() -> pubSubDataHandler.saveDataToFirestore(vehicleData),
                "Saving data to Firestore should not throw an exception");

        verify(mockFirestore.collection("Car")).document();
        verify(mockDocumentReference).set(vehicleData);
        verify(mockApiFuture).get();
    }

    @Test
    public void test_fetchConversionRateFromAPI_validInput() throws Exception {
        String toCurrency = "INR";
        // Call the method under test
        double conversionRate = pubSubDataHandler.fetchConversionRateFromAPI(toCurrency);
        // Verify the result
        assertNotEquals(0, conversionRate, 0.0);
    }
}
