package com.nashtech.service.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.spring.data.firestore.FirestoreDataException;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.nashtech.entity.GCPCarEntity;
import com.nashtech.model.Car;
import com.nashtech.model.CarBrand;
import com.nashtech.repository.FirestoreDbRepository;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class FirestoreDbServiceTest {

    @Mock
    private Publisher publisher;
    @Mock
    private FluxSink<ServerSentEvent<Map<String, String>>> emitter;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Firestore firestore;
    @Mock
    private FirestoreDbRepository firestoreDbRepository;

    @InjectMocks
    private FirestoreDbService firestoreDbService;
    @Mock
    private CollectionReference collectionReference;
    @Mock
    private QuerySnapshot snapshotsMock;

    @Mock
    QueryDocumentSnapshot doc;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();

        firestoreDbService.setObjectMapper(objectMapper);
        firestoreDbService.setPublisher(publisher);
    }

    @Test
    void testGetCarsByBrand() {
        // Setup
        // Configure FirestoreDbRepository.findByBrand(...).
        final GCPCarEntity gcpCarEntity = new GCPCarEntity();
        gcpCarEntity.setCarId(0);
        gcpCarEntity.setModel("carModel");
        gcpCarEntity.setBrand("brand");
        gcpCarEntity.setYear(2020L);
        gcpCarEntity.setColor("color");
        gcpCarEntity.setMileage(0.0);
        gcpCarEntity.setPrice(0.0);
        final Flux<GCPCarEntity> gcpCarEntityFlux = Flux.just(gcpCarEntity);
        when(firestoreDbRepository.findByBrand("brand")).thenReturn(gcpCarEntityFlux);

        // Run the test
        final Flux<Car> result = firestoreDbService.getCarsByBrand("brand");

        // Verify the results
        StepVerifier.create(result)
                .expectNextMatches(car -> car.getBrand().equals("brand"))
                .expectComplete()
                .verify();
    }

    @Test
    void testGetCarsByBrand_FirestoreDbRepositoryReturnsError() {
        // Setup
        Mockito.when(firestoreDbRepository.findByBrand("brand")).thenReturn(Flux.error(new FirestoreDataException("Something Went Wrong !!, Data not found")));

        // Run the test
        final Flux<Car> result = firestoreDbService.getCarsByBrand("brand");

        // Verify the results
        StepVerifier.create(result)
                .expectError(FirestoreDataException.class)
                .verify();
    }

    @Test
    void testGetCarsByBrand_FirestoreDbRepositoryReturnsNoItem() {
        // Setup
        when(firestoreDbRepository.findByBrand("brand")).thenReturn(Flux.empty());

        // Run the test
        final Flux<Car> result = firestoreDbService.getCarsByBrand("brand");

        // Verify the results
        StepVerifier.create(result)
                .expectError(FirestoreDataException.class)
                .verify();
    }


    @Test
    void test_publisher_not_null_shutdown_and_await_termination() throws InterruptedException {
        FirestoreDbService firestoreDbService = new FirestoreDbService(mock(Firestore.class));
        ReflectionTestUtils.setField(firestoreDbService, "publisher", publisher);

        firestoreDbService.cleanup();

        verify(publisher).shutdown();
        verify(publisher).awaitTermination(1, TimeUnit.MINUTES);
    }


    @Test
    void test_interrupted_exception_caught_while_shutting_down_publisher_log_error_message() throws InterruptedException {
        FirestoreDbService firestoreDbService = new FirestoreDbService(mock(Firestore.class));
        ReflectionTestUtils.setField(firestoreDbService, "publisher", publisher);
        doThrow(new InterruptedException("Interrupted")).when(publisher).awaitTermination(1, TimeUnit.MINUTES);

        // Configure SLF4J to capture logs
        Logger logger = (Logger) LoggerFactory.getLogger(FirestoreDbService.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        try {
            // Act
            firestoreDbService.cleanup();

            // Assert
            List<ILoggingEvent> logs = listAppender.list;
            assertEquals(1, logs.size());
            ILoggingEvent loggingEvent = logs.get(0);
            assertEquals(Level.ERROR, loggingEvent.getLevel());
            assertTrue(loggingEvent.getMessage().contains("Error while shutting down Publisher:"));
        } finally {
            logger.detachAppender(listAppender);
            listAppender.stop();
        }


    }

    @Test
    void test_publisher_null_does_not_shutdown_or_await_termination() throws InterruptedException {
        FirestoreDbService firestoreDbService = new FirestoreDbService(mock(Firestore.class));
        ReflectionTestUtils.setField(firestoreDbService, "publisher", null);

        firestoreDbService.cleanup();

        // Verify that the methods are not called when publisher is null
        verify(publisher, never()).shutdown();
        verify(publisher, never()).awaitTermination(1, TimeUnit.MINUTES);
    }

    @Test
    void test_return_mono_void_on_publishing_completion() {
        Car testCar = new Car(22, "Toyota", "Camry", 2023L, "red", 2223.0, 22253.12, 1, 1.2);

        Mono<Void> result = firestoreDbService.pushData(testCar);

        StepVerifier.create(result)
                .expectComplete()
                .verify();
    }

    /////getAllBrands()
    @Test
    void test_retrieve_all_car_brands() {
        // Arrange
        List<GCPCarEntity> carEntities = Arrays.asList(
                new GCPCarEntity("1", 1, "Model1", "Brand1", 2021L, "Red", 1000.0, 20000.0, 10, 5.0),
                new GCPCarEntity("2", 2, "Model2", "Brand2", 2022L, "Blue", 2000.0, 30000.0, 20, 6.0)
        );
        when(firestoreDbRepository.findAll()).thenReturn(Flux.fromIterable(carEntities));

        // Act
        Flux<CarBrand> result = firestoreDbService.getAllBrands();

        // Assert
        StepVerifier.create(result)
                .expectNext(new CarBrand("Brand1"))
                .expectNext(new CarBrand("Brand2"))
                .verifyComplete();
    }

    @Test
    void test_filter_out_null_brand_car_entities() {
        // Arrange
        List<GCPCarEntity> carEntities = Arrays.asList(
                new GCPCarEntity("1", 1, "Model1", "Brand1", 2021L, "Red", 1000.0, 20000.0, 10, 5.0),
                new GCPCarEntity("2", 2, "Model2", null, 2022L, "Blue", 2000.0, 30000.0, 20, 6.0)
        );
        when(firestoreDbRepository.findAll()).thenReturn(Flux.fromIterable(carEntities));

        // Act
        Flux<CarBrand> result = firestoreDbService.getAllBrands();

        // Assert
        StepVerifier.create(result)
                .expectNext(new CarBrand("Brand1"))
                .verifyComplete();
    }

    @Test
    void testGetAllBrands_FirestoreDbRepositoryReturnsError() {
        // Setup
        // Configure FirestoreDbRepository.findAll(...).
        final Flux<GCPCarEntity> gcpCarEntityFlux = Flux.error(new FirestoreDataException("message"));
        when(firestoreDbRepository.findAll()).thenReturn(gcpCarEntityFlux);

        // Run the test
        final Flux<CarBrand> result = firestoreDbService.getAllBrands();

        // Verify the results
        StepVerifier.create(result)
//                .expectErrorMatches(throwable -> throwable.getMessage().equals("message"))
                .expectError(FirestoreDataException.class)
                .verify();
    }

    //////// for line  return Mono.empty();
    @Test
    void test_valid_car_object() {
        Car testCar = new Car(22, "Toyota", "Camry", 2023L, "red", 2223.0, 22253.12, 1, 1.2);

        Mono<Void> result = firestoreDbService.pushData(testCar);

        StepVerifier.create(result)
                .expectComplete()
                .verify();
    }

    @Test
    void test_publishing_failure() {
        Car testCar = new Car(22, "Toyota", "Camry", 2023L, "red", 2223.0, 22253.12, 1, 1.2);
        Logger log = mock(Logger.class);
        //Publisher publisher = mock(Publisher.class);
        when(publisher.publish(any(PubsubMessage.class))).thenThrow(new RuntimeException("Simulated publishing error"));

        firestoreDbService.setPublisher(publisher);

        Mono<Void> result = firestoreDbService.pushData(testCar);

        StepVerifier.create(result)
                .expectError()
                .verify();
    }

    @Test
    void test_serialization_failure() throws JsonProcessingException {
        //ObjectMapper objectMapper = mock(ObjectMapper.class);

        FirestoreDbService dbService = new FirestoreDbService(mock(Firestore.class));
        dbService.setObjectMapper(objectMapper);

        Car testCar = new Car();

        when(objectMapper.writeValueAsString(testCar)).thenThrow(new JsonProcessingException("Simulated serialization error") {
        });

        Mono<Void> result = dbService.pushData(testCar);

        StepVerifier.create(result)
                .expectError()
                .verify();
    }

    @Test
    void testGetAllBrandsSse() throws InterruptedException {

        when(firestore.collection("Car")).thenReturn(collectionReference);
        when(collectionReference.addSnapshotListener(any())).thenAnswer(invocation -> {
            when(snapshotsMock.iterator()).thenReturn(Collections.singletonList(doc).iterator());
            ((EventListener<QuerySnapshot>) invocation.getArgument(0)).onEvent(snapshotsMock, null);
            return null;
        });

        // Test
        FirestoreDbService dbService = new FirestoreDbService(firestore);

        // Use CountDownLatch to wait for asynchronous processing to complete
        CountDownLatch latch = new CountDownLatch(1);

        // Set up StepVerifier
        StepVerifier.create(dbService.getAllBrandsSse())
                .then(() -> {
                    // Simulate asynchronous processing completion
                    latch.countDown();
                })
                .thenAwait(Duration.ofSeconds(5)) // Adjust the duration as needed
                .thenCancel()
                .verify();

        // Wait for the latch to ensure that the asynchronous processing is completed
        latch.await(10, TimeUnit.SECONDS);
    }


}

