package com.nashtech.shipment.aggregate;

import com.nashtech.common.command.CreateShipmentCommand;
import com.nashtech.common.event.OrderShippedEvent;
import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.common.model.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@Getter
public class ShipmentAggregate {

    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String paymentId;
    private String productId;
    private Integer quantity;
    private String brand;
    private Double basePrice;
    private Double subTotal;
    private Double total;
    private Float tax;
    private Float totalTax;
    private User user;

    public ShipmentAggregate() {
    }

    @CommandHandler
    public ShipmentAggregate(CreateShipmentCommand createShipmentCommand) {
        log.info("CreateShipmentCommand is called for shipmentId: {}", createShipmentCommand.getShipmentId());
        ShipmentCreatedEvent shipmentCreatedEvent = ShipmentCreatedEvent.builder()
                    .shipmentId(createShipmentCommand.getShipmentId())
                    .orderId(createShipmentCommand.getOrderId())
                    .paymentId(createShipmentCommand.getPaymentId())
                    .productId(createShipmentCommand.getProductId())
                    .quantity(createShipmentCommand.getQuantity())
                    .brand(createShipmentCommand.getBrand())
                    .basePrice(createShipmentCommand.getBasePrice())
                    .tax(createShipmentCommand.getTax())
                    .totalTax(createShipmentCommand.getTotalTax())
                    .subTotal(createShipmentCommand.getSubTotal())
                    .total(createShipmentCommand.getTotal())
                    .user(createShipmentCommand.getUser())
                    .build();
        AggregateLifecycle.apply(shipmentCreatedEvent);

        OrderShippedEvent shippedEvent = OrderShippedEvent.builder()
                .orderId(createShipmentCommand.getOrderId())
                .paymentId(createShipmentCommand.getPaymentId())
                .shipmentId(createShipmentCommand.getShipmentId())
                .build();
        AggregateLifecycle.apply(shippedEvent);
    }

    @EventSourcingHandler
    public  void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        this.shipmentId = shipmentCreatedEvent.getShipmentId();
        this.orderId = shipmentCreatedEvent.getOrderId();
        this.paymentId = shipmentCreatedEvent.getPaymentId();
        this.productId = shipmentCreatedEvent.getProductId();
        this.brand = shipmentCreatedEvent.getBrand();
        this.quantity = shipmentCreatedEvent.getQuantity();
        this.basePrice = shipmentCreatedEvent.getBasePrice();
        this.subTotal = shipmentCreatedEvent.getSubTotal();
        this.total = shipmentCreatedEvent.getTotal();
        this.tax = shipmentCreatedEvent.getTax();
        this.totalTax = shipmentCreatedEvent.getTotalTax();
        this.user = shipmentCreatedEvent.getUser();
    }

}
