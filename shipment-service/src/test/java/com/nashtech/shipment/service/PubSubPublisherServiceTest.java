package com.nashtech.shipment.service;

import com.google.api.core.ApiFutures;
import com.google.api.core.SettableApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.PubsubMessage;
import com.nashtech.shipment.config.GCPConfig;
import com.nashtech.shipment.entity.ShipmentEntity;
import com.nashtech.shipment.util.ObjectCreator;
import com.nashtech.shipment.util.TestTag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Tag(TestTag.SMALL_TESTS)
class PubSubPublisherServiceTest {
    @Mock
    private Publisher mockPublisher;

    @Mock
    private GCPConfig mockGCPConfig;

    private PubSubPublisherService pubSubPublisherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown() {
        pubSubPublisherService.cleanup();
    }

    @Test
    void testMessagePublisher() {
        ShipmentEntity shipmentEntity = ObjectCreator.getShipmentEntity();
        when(mockGCPConfig.getProjectId()).thenReturn("testProjectId");
        when(mockGCPConfig.getTopicId()).thenReturn("testTopicId");
        when(mockPublisher.publish(any(PubsubMessage.class)))
                .thenReturn(ApiFutures.immediateFuture("FutureValue"));
        pubSubPublisherService = new PubSubPublisherService(mockGCPConfig, mockPublisher);
        pubSubPublisherService.messagePublisher(shipmentEntity);

        verify(mockPublisher, times(1)).publish(any(PubsubMessage.class));
    }

    @Test
    void testMessageNotPublished() {
        ShipmentEntity shipmentEntity = new ShipmentEntity();
        when(mockGCPConfig.getProjectId()).thenReturn("testProjectId");
        when(mockGCPConfig.getTopicId()).thenReturn("testTopicId");
        SettableApiFuture<String> future = SettableApiFuture.create();
        future.setException(new Exception("Simulated error"));

        when(mockPublisher.publish(any(PubsubMessage.class))).thenReturn(future);

        pubSubPublisherService = new PubSubPublisherService(mockGCPConfig, mockPublisher);

        try {
            pubSubPublisherService.messagePublisher(shipmentEntity);
        } catch (Exception exception) {
            assertInstanceOf(Exception.class, exception);

            assertEquals("Simulated error", exception.getMessage());
        }

        verify(mockPublisher, times(1)).publish(any(PubsubMessage.class));
    }


}