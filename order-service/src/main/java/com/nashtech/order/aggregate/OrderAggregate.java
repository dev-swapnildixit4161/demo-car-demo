package com.nashtech.order.aggregate;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.commands.ApproveOrderCommand;
import com.nashtech.order.commands.CreateOrderCommand;
import com.nashtech.order.commands.RejectOrderCommand;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    String productId;
    String userId;
    String paymentId;
    String shipmentId;
    OrderStatus orderStatus;
    String reasonToRejectedOrder;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .orderId(createOrderCommand.getOrderId())
                .productId(createOrderCommand.getProductId())
                .quantity(createOrderCommand.getQuantity())
                .userId(createOrderCommand.getUserId())
                .orderStatus(OrderStatus.ORDER_PARTIALLY_APPROVED)
                .build();
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        this.orderId = orderCreatedEvent.getOrderId();
        this.productId = orderCreatedEvent.getProductId();
        this.userId = orderCreatedEvent.getUserId();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
    }

    @CommandHandler
    public void handle(ApproveOrderCommand approveOrderCommand) {
        OrderApprovedEvent orderApprovedEvent = OrderApprovedEvent.builder()
                .orderId(approveOrderCommand.getOrderId())
                .paymentId(approveOrderCommand.getPaymentId())
                .shipmentId(approveOrderCommand.getShipmentId())
                .orderStatus(approveOrderCommand.getOrderStatus())
                .build();
        AggregateLifecycle.apply(orderApprovedEvent);
    }

    @EventSourcingHandler
    public void on(OrderApprovedEvent event) {
        this.paymentId = event.getPaymentId();
        this.shipmentId = event.getShipmentId();
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(RejectOrderCommand rejectOrderCommand) {
        OrderCancelledEvent orderCancelledEvent = OrderCancelledEvent.builder()
                .orderId(rejectOrderCommand.getOrderId())
                .productId(rejectOrderCommand.getProductId())
                .paymentId(rejectOrderCommand.getPaymentId())
                .shipmentId(rejectOrderCommand.getShipmentId())
                .userId(rejectOrderCommand.getUserId())
                .reasonToFailed(rejectOrderCommand.getReasonToFailed())
                .orderStatus(rejectOrderCommand.getOrderStatus())
                .build();
        AggregateLifecycle.apply(orderCancelledEvent);
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        this.productId = event.getProductId();
        this.paymentId = event.getPaymentId();
        this.shipmentId = event.getShipmentId();
        this.reasonToRejectedOrder = event.getReasonToFailed();
        this.orderStatus = event.getOrderStatus();
    }

}
