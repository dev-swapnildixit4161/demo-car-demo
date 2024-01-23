package com.nashtech.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.spring.data.firestore.FirestoreDataException;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.nashtech.exception.DataNotFoundException;
import com.nashtech.exception.MyCustomException;
import com.nashtech.model.Car;
import com.nashtech.model.CarBrand;
import com.nashtech.repository.FirestoreDbRepository;
import com.nashtech.service.CloudDataService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Service implementation class for
 * publishing vehicle data to Google Cloud Pub/Sub.
 */
@Slf4j
@Service
@Profile("firestore")
@AllArgsConstructor
public class FirestoreDbService implements CloudDataService {

    /**
     * The VehicleRepository instance used to retrieve car information.
     */
    private FirestoreDbRepository firestoreDbRepository;

    /**
     * The Google Cloud Platform project ID
     * used for publishing vehicle data to Pub/Sub.
     */
    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    /**
     * The Firestore instance.
     * used for publishing data to firestore.
     */
    private Firestore firestore;

    /**
     * The FirestoreDbService constructor.
     *
     * @param fireStoreInstance firestore instance.
     */
    public FirestoreDbService(final Firestore fireStoreInstance) {
        this.firestore = fireStoreInstance;

    }



    /**
     * The ID of the Pub/Sub topic to
     * which the vehicle data will be published.
     */
    @Value("${google.pubSub.topic}")
    private String topicId;

    /**
     * Static Publisher instance for asynchronous vehicle
     * data publishing to the Google Cloud Pub/Sub topic.
     */
    @Setter
    private Publisher publisher;

    /**
     * The Jackson ObjectMapper used
     * for serialization and deserialization of JSON data.
     */
    @Setter
    private ObjectMapper objectMapper;


    /**
     * Initializes the static Publisher instance for
     * vehicle data publishing to the Google Cloud Pub/Sub topic.
     * <p>
     * This method is annotated with @PostConstruct
     * and is automatically called after the bean is constructed,
     * ensuring that the Publisher is ready for use.
     *
     * @throws IOException If an error occurs during the
     *                     initialization of the Publisher.
     */
    @PostConstruct
    public void init() throws IOException {
        TopicName topicName = TopicName.of(projectId, topicId);
        publisher = Publisher.newBuilder(topicName).build();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Cleans up and shuts down the static Publisher instance
     * used for vehicle data publishing.
     * This method is annotated with @PreDestroy
     * and is automatically called before the bean is destroyed,
     * ensuring that the Publisher is properly shutdown and
     * resources are released.
     */
    @PreDestroy
    public void cleanup()  {
        try {
            if (publisher != null) {
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        } catch (InterruptedException interruptedException) {
            // Re-interrupt the thread or rethrow the InterruptedException
            Thread.currentThread().interrupt();
            log.error("Error while shutting down Publisher: {}",
                    interruptedException.getMessage());
        }
    }


    /**
     * Publishes vehicle data to the Google Cloud Pub/Sub topic.
     *
     * @param cars The ReactiveDataCars representing
     *             the vehicle data to be published.
     * @return A Mono<Void> representing the completion of the
     * publishing process.
     */
    public Mono<Void> pushData(final Car cars) {
        String vehicleJson;
        try {
            vehicleJson = objectMapper.writeValueAsString(cars);
        } catch (JsonProcessingException exception) {
            return Mono.error(exception);
        }

        ByteString data = ByteString.copyFromUtf8(vehicleJson);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

        return Mono.fromRunnable(() -> publisher.publish(pubsubMessage))
                .doOnError(error -> {
                   throw new MyCustomException("Error occurred during publishing", error);
                }).then();
    }

    /**
     * Retrieves all CarBrands from Firestore database.
     *
     * @return A Flux of CarBrand objects.
     */
    @Override
    public Flux<CarBrand> getAllBrands() {
        return firestoreDbRepository.findAll()
                .filter(gcpCarEntity -> gcpCarEntity.getBrand() != null)
                .map(gcpCarEntity -> new CarBrand(gcpCarEntity.getBrand()))
                .distinct()
                .doOnComplete(() -> log.info("Data retrieved successfully"))
                .switchIfEmpty(Flux.error(new DataNotFoundException()))
                .onErrorResume(throwable -> {
                    log.error("Error occurred during data retrieval: "
                            + throwable.getMessage());
                    return Flux.error(
                            new FirestoreDataException(
                                    "Failed to retrieve car brands.",
                                    throwable));
                });
    }

    /**
     * Retrieves all Car objects by a given brand from Firestore database.
     *
     * @param brand The brand of the Car to filter by.
     * @return A Flux of Car objects matching the given brand.
     */
    @Override
    public Flux<Car> getCarsByBrand(final String brand) {
        return firestoreDbRepository.findByBrand(brand)
                .filter(Objects::nonNull)
                .map(gcpCarEntity -> Car.builder()
                        .carId(gcpCarEntity.getCarId())
                        .model(gcpCarEntity.getModel())
                        .brand(gcpCarEntity.getBrand())
                        .year(gcpCarEntity.getYear())
                        .color(gcpCarEntity.getColor())
                        .mileage(gcpCarEntity.getMileage())
                        .price(gcpCarEntity.getPrice())
                        .build())
                .distinct()
                .switchIfEmpty(Flux.error(new DataNotFoundException()))
                .doOnComplete(() ->
                        log.info("Received Car Details successfully"))
                .onErrorResume(throwable -> {
                    log.error("Error occurred during data retrieval: "
                            + throwable.getMessage());
                    return Flux.error(
                            new FirestoreDataException(
                                    "Failed to retrieve car Information.",
                                    throwable));
                });

    }

    /**
     * Retrieves all CarBrands from Firestore database.
     *
     * @return A Flux of CarBrand objects.
     */
    public Flux<ServerSentEvent<Map<String, String>>> getAllBrandsSse() {
        Set<String> emittedBrands = Collections
                .synchronizedSet(new HashSet<>());

        return Flux.<ServerSentEvent<Map<String, String>>>create(emitter -> firestore.collection("Car")
                .addSnapshotListener((snapshots, e) -> {
                    if (e != null) {
                        log.error("Error in Firestore snapshot listener",
                                e);
                        emitter.error(e);
                        return;
                    }

                    for (QueryDocumentSnapshot doc : snapshots) {
                        Map<String, Object> data = doc.getData();
                        String brand = (String) data.get("brand");

                        synchronized (emittedBrands) {
                            if (!emittedBrands.contains(brand)) {
                                emittedBrands.add(brand);

                                processAndEmitEvent(emitter, brand)
                                        .subscribeOn(Schedulers.parallel())
                                        .subscribe();
                            }
                        }
                    }
                })).concatWith(Flux.never());
    }

    public Mono<Void> processAndEmitEvent(final FluxSink<ServerSentEvent<
            Map<String, String>>> emitter, final String brand) {
        return Mono.fromRunnable(() -> {
            Map<String, String> eventData = new HashMap<>();
            eventData.put("brand", brand);

            ServerSentEvent<Map<String, String>> event = ServerSentEvent
                    .<Map<String, String>>builder()
                    .data(eventData)
                    .build();

            emitter.next(event);
        });
    }


}