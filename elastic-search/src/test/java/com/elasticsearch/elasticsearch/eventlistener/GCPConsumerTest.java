package com.elasticsearch.elasticsearch.eventlistener;

import com.elasticsearch.elasticsearch.eventlistener.impl.GcpConsumer;
import com.elasticsearch.elasticsearch.repository.CarEntityRepository;
import com.elasticsearch.elasticsearch.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.pubsub.v1.PubsubMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GcpConsumerTest {

    @Mock
    private PubSubTemplate pubSubTemplate;

    @Mock
    private CarEntityRepository carEntityRepository;

    @Mock
    private CarService carService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GcpConsumer gcpConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void consumeEventTest() {
        BasicAcknowledgeablePubsubMessage mockMessage = Mockito.mock(BasicAcknowledgeablePubsubMessage.class);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(com.google.protobuf.ByteString.copyFromUtf8("test")).build();
        when(mockMessage.getPubsubMessage()).thenReturn(pubsubMessage);

        assertDoesNotThrow(() -> gcpConsumer.consumeEvent(mockMessage));

        verify(mockMessage).ack();
    }

    @Test
    void processMessageShouldHandleJsonProcessingException() throws JsonProcessingException {
        BasicAcknowledgeablePubsubMessage mockMessage = Mockito.mock(BasicAcknowledgeablePubsubMessage.class);
        when(mockMessage.getPubsubMessage()).thenReturn(createPubsubMessage("invalid JSON"));
        when(objectMapper.readValue(any(String.class), any(Class.class))).thenThrow(JsonProcessingException.class);

        assertDoesNotThrow(() -> gcpConsumer.processMessage(mockMessage));

        verify(mockMessage).ack();
    }

    private PubsubMessage createPubsubMessage(String data) {
        return PubsubMessage.newBuilder().setData(com.google.protobuf.ByteString.copyFromUtf8(data)).build();
    }
}