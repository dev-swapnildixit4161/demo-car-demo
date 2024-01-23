package com.elasticsearch.elasticsearch.eventlistener.impl;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.eventlistener.CloudConsumer;
import com.elasticsearch.elasticsearch.repository.CarEntityRepository;
import com.elasticsearch.elasticsearch.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.pubsub.v1.PubsubMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("gcp")
public class GcpConsumer implements CloudConsumer<BasicAcknowledgeablePubsubMessage> {

    @Value("${pubSub.subscriptionId}")
    private String subscription;

    private final PubSubTemplate pubSubTemplate;
    private final CarService service;

    private final ObjectMapper objectMapper;

    private final CarEntityRepository carEntityRepository;

    @Autowired
    public GcpConsumer(PubSubTemplate pubSubTemplate, CarService service, ObjectMapper objectMapper,
                       CarEntityRepository carEntityRepository) {
        this.pubSubTemplate = pubSubTemplate;
        this.service = service;
        this.objectMapper = objectMapper;
        this.carEntityRepository = carEntityRepository;
    }

    public String subscription() {
        return this.subscription;
    }

    public java.util.function.Consumer<BasicAcknowledgeablePubsubMessage> messageConsumer() {
        return this::consumeEvent;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        log.info("Subscribing {} to {} ", this.getClass().getSimpleName(), this.subscription());
        pubSubTemplate.subscribe(this.subscription(), this.messageConsumer());
    }

    @Override
    public void consumeEvent(BasicAcknowledgeablePubsubMessage basicAcknowledgeablePubsubMessage) {
        PubsubMessage message = basicAcknowledgeablePubsubMessage.getPubsubMessage();
        log.info("Message received from {}", basicAcknowledgeablePubsubMessage.getProjectSubscriptionName());
        try {
            log.info("Message: {}", message.getData().toStringUtf8());
            processMessage(basicAcknowledgeablePubsubMessage);
        } catch (Exception ex) {
            log.error("Error Occurred while receiving pubsub message:::::", ex);
        }
        basicAcknowledgeablePubsubMessage.ack();
    }

    public void processMessage(BasicAcknowledgeablePubsubMessage message) {
        CarEntity[] gcHubMessageArray;
        String eventMsgString = "";
        try {
            eventMsgString = message.getPubsubMessage().getData().toStringUtf8();
            gcHubMessageArray = objectMapper.readValue(eventMsgString, CarEntity[].class);
            CarEntity[] gcpElasticCarEntityList = gcHubMessageArray;
            for (CarEntity carEntity : gcpElasticCarEntityList) {
                CarEntity existingCarEntity = carEntityRepository.findByCarId(carEntity.getCarId());
                if (existingCarEntity == null) {
                    service.saveCarEntity(carEntity);
                    log.info("Car details saved successfully with car Id: {}", carEntity.getCarId());
                } else {
                    log.info("Car details cannot be saved because car with car Id: {} is already present", carEntity.getCarId());
                }
            }
        } catch (IllegalArgumentException | JsonProcessingException e) {
            log.error("Json exception in parsing message: {}", eventMsgString, e);
            message.ack();
        }
    }
}