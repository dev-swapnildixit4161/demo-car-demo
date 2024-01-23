package com.elasticsearch.elasticsearch.eventlistener.impl;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.eventlistener.CloudConsumer;
import com.elasticsearch.elasticsearch.service.CarService;
import com.elasticsearch.elasticsearch.util.CarMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("azure")
public class AzureConsumer implements CloudConsumer<String> {
    private final CarService service;

    @Autowired
    public AzureConsumer(CarService service) {
        this.service = service;
    }

    @KafkaListener(topics = "${topic.producer}")
    public void consumeEvents(ConsumerRecord<String,String> event)  {

        log.info("Received message from kafka queue: {}", event.value());
        CarEntity carEntity = CarMapper.mapStringToEntity(event.value());
       service.saveCarEntity(carEntity);
       log.info(carEntity.toString());
    }
    public void consumeEvent(String event) {
        log.info("Received message from kafka queue: {}", event);
    }
}
