package com.nashtech.shipment.handler;

import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.shipment.entity.ShipmentEntity;
import com.nashtech.shipment.repository.ShipmentRepository;
import com.nashtech.shipment.service.PubSubPublisherService;
import com.nashtech.shipment.util.ObjectCreator;
import com.nashtech.shipment.util.TestTag;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@Tag(TestTag.SMALL_TESTS)
class ShipmentEventHandlerTest {

    @Test
    void testShipmentEventHandler() {

        ShipmentRepository mockShipmentRepository = Mockito.mock(ShipmentRepository.class);
        PubSubPublisherService mockPubSubPublisherService = Mockito.mock(PubSubPublisherService.class);

        ShipmentEventHandler shipmentEventHandler = new ShipmentEventHandler(mockShipmentRepository, mockPubSubPublisherService);

        ShipmentCreatedEvent shipmentCreatedEvent = ObjectCreator.getShipmentCreatedEvent();


        shipmentEventHandler.on(shipmentCreatedEvent);


        Mockito.verify(mockShipmentRepository, times(1)).save(any(ShipmentEntity.class));
        Mockito.verify(mockPubSubPublisherService, times(1)).messagePublisher(any(ShipmentEntity.class));
    }

}
