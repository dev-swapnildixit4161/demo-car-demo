package com.nashtech.shipment.handler;

import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.common.model.User;
import com.nashtech.shipment.entity.ShipmentEntity;
import com.nashtech.shipment.repository.ShipmentRepository;
import com.nashtech.shipment.service.PubSubPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShipmentEventHandler {

    private final ShipmentRepository shipmentRepository;
    private final PubSubPublisherService pubSubPublisherService;

    public ShipmentEventHandler(ShipmentRepository shipmentRepository, PubSubPublisherService pubSubPublisherService) {
        this.shipmentRepository = shipmentRepository;
        this.pubSubPublisherService = pubSubPublisherService;
    }

    @EventHandler
    public void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        log.info("ShipmentCreatedEvent is called for shipmentId: {}", shipmentCreatedEvent.getShipmentId());
        User user = shipmentCreatedEvent.getUser();
        ShipmentEntity shipmentEntity = new ShipmentEntity(
                shipmentCreatedEvent.getShipmentId(),
                shipmentCreatedEvent.getOrderId(),
                shipmentCreatedEvent.getPaymentId(),
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAddress(),
                user.getEmailId(),
                user.getMobileNumber(),
                shipmentCreatedEvent.getProductId(),
                shipmentCreatedEvent.getBrand(),
                shipmentCreatedEvent.getQuantity(),
                shipmentCreatedEvent.getBasePrice(),
                shipmentCreatedEvent.getSubTotal(),
                shipmentCreatedEvent.getTotal(),
                shipmentCreatedEvent.getTax(),
                shipmentCreatedEvent.getTotalTax()
        );
        log.info("Saving Shipment details to database");
        shipmentRepository.save(shipmentEntity);

        log.info("Sending Shipment details to pub sub topic");
        pubSubPublisherService.messagePublisher(shipmentEntity);
    }

}
