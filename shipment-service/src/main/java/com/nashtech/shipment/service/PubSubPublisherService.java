package com.nashtech.shipment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.nashtech.shipment.config.GCPConfig;
import com.nashtech.shipment.entity.ShipmentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PubSubPublisherService {
    private Publisher publisher;
    @SuppressWarnings("java:S6813")
    @Autowired
    private GCPConfig gcpConfig;
    private ObjectMapper objectMapper;

    public PubSubPublisherService(GCPConfig gcpConfig, Publisher publisher) {
        this.gcpConfig = gcpConfig;
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.publisher = publisher;
    }

    public void init() throws IOException {
        TopicName topicName = TopicName.of(gcpConfig.getProjectId(), gcpConfig.getTopicId());
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        if (publisher == null) {
            publisher = Publisher.newBuilder(topicName).build();
        }
    }

    public void cleanup() {
        try {
            if (publisher != null) {
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        } catch (InterruptedException interruptedException) {
            log.error("Error while shutting down Publisher: {}",
                    interruptedException.getMessage());
        }
    }


    public void messagePublisher(final ShipmentEntity shipmentEntity) {
        log.info("Publishing data to topic: {}", gcpConfig.getTopicId());

        try {
            init();
            String shipmentData = objectMapper.writeValueAsString(shipmentEntity);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8(shipmentData)).build();
            ApiFuture<String> publishedMessage = publisher.publish(pubsubMessage);
            log.info("Message id generated:{}", publishedMessage.get());
        } catch (Exception exception) {
            log.error("Error : {} while publishing data to pub sub topic : {}", exception.getMessage(), gcpConfig.getTopicId());
        } finally {
            cleanup();
        }
    }

}
