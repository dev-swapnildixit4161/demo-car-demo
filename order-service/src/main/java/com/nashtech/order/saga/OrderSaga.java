package com.nashtech.order.saga;

import com.nashtech.common.command.CancelPaymentCommand;
import com.nashtech.common.command.CancelProductReserveCommand;
import com.nashtech.common.command.CreateShipmentCommand;
import com.nashtech.common.command.ProcessPaymentCommand;
import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.common.event.OrderShippedEvent;
import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.common.event.PaymentCancelledEvent;
import com.nashtech.common.event.ProductReserveFailedEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.common.event.ShipmentCancelledEvent;
import com.nashtech.common.utils.OderFailure;
import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.commands.ApproveOrderCommand;
import com.nashtech.order.commands.RejectOrderCommand;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.exception.CompensateOrder;
import com.nashtech.order.query.FindOrderQuery;
import com.nashtech.order.restapi.response.OrderSummary;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@Saga
@Slf4j
public class OrderSaga {

    private final CommandGateway commandGateway;

    private final QueryUpdateEmitter queryUpdateEmitter;

    @Autowired
    public OrderSaga(CommandGateway commandGateway,QueryUpdateEmitter queryUpdateEmitter) {
        this.commandGateway = commandGateway;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        log.info("Order Saga started for Order Id : {}", orderCreatedEvent.getOrderId());

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(orderCreatedEvent.getProductId())
                .orderId(orderCreatedEvent.getOrderId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();

        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Start a compensating transaction
                CompensateOrder compensateOrder = new CompensateOrder();
                compensateOrder.setOrderId(reserveProductCommand.getOrderId());
                compensateOrder.setProductId(orderCreatedEvent.getProductId());
                compensateOrder.setUserId(orderCreatedEvent.getUserId());
                compensateOrder.setReasonToFailed(simplifyErrorMessage(commandResultMessage,
                        OderFailure.INVENTORY_SERVICE_NOT_AVAILABLE));
                orderRejectedCommand(compensateOrder);
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        log.info(String.format("ProductReservedEvent started for productId: %s and orderId: %s ",
                productReservedEvent.getProductId(), productReservedEvent.getOrderId()));

        ProcessPaymentCommand processPaymentCommand = ProcessPaymentCommand.builder()
                .paymentId(UUID.randomUUID().toString()) // payment Id generation
                .orderId(productReservedEvent.getOrderId())
                .userId(productReservedEvent.getUserId())
                .productId(productReservedEvent.getProductId())
                .brand(productReservedEvent.getBrand())
                .quantity(productReservedEvent.getQuantity())
                .tax(productReservedEvent.getTax())
                .basePrice(productReservedEvent.getBasePrice())
                .totalTax(productReservedEvent.getTotalTax())
                .total(productReservedEvent.getTotal())
                .subTotal(productReservedEvent.getSubTotal())
                .build();


        commandGateway.send(processPaymentCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Start a compensating transaction
                CompensateOrder compensateOrder = new CompensateOrder();
                compensateOrder.setOrderId(productReservedEvent.getOrderId());
                compensateOrder.setProductId(productReservedEvent.getProductId());
                compensateOrder.setUserId(productReservedEvent.getUserId());
                compensateOrder.setPaymentId(processPaymentCommand.getPaymentId());
                compensateOrder.setReasonToFailed(simplifyErrorMessage(commandResultMessage,
                        OderFailure.PAYMENT_SERVICE_NOT_AVAILABLE));

                CancelProductReserveCommand cancelProductReserveCommand = CancelProductReserveCommand.builder()
                        .productId(productReservedEvent.getProductId())
                        .userId(productReservedEvent.getUserId())
                        .orderId(productReservedEvent.getOrderId())
                        .quantity(productReservedEvent.getQuantity())
                        .build();
                commandGateway.send(cancelProductReserveCommand);

                orderRejectedCommand(compensateOrder);
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReserveFailedEvent productReserveFailedEvent) {
        // Start the compensating transaction
        log.info("ProductReserveFailedEvent started for orderId : {}", productReserveFailedEvent.getOrderId());
        CompensateOrder compensateOrder = new CompensateOrder();
        compensateOrder.setOrderId(productReserveFailedEvent.getOrderId());
        compensateOrder.setProductId(productReserveFailedEvent.getProductId());
        compensateOrder.setUserId(productReserveFailedEvent.getUserId());
        compensateOrder.setReasonToFailed(productReserveFailedEvent.getReasonToFailed());
        orderRejectedCommand(compensateOrder);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentApprovedEvent paymentApprovedEvent) {
        log.info("PaymentProcessedEvent started for paymentId : {}", paymentApprovedEvent.getPaymentId());

        CreateShipmentCommand createShipmentCommand = CreateShipmentCommand.builder()
                .shipmentId(UUID.randomUUID().toString()) //Shipment Id generation
                .paymentId(paymentApprovedEvent.getPaymentId())
                .user(paymentApprovedEvent.getUser())
                .orderId(paymentApprovedEvent.getOrderId())
                .productId(paymentApprovedEvent.getProductId())
                .brand(paymentApprovedEvent.getBrand())
                .total(paymentApprovedEvent.getTotal())
                .subTotal(paymentApprovedEvent.getSubTotal())
                .tax(paymentApprovedEvent.getTax())
                .quantity(paymentApprovedEvent.getQuantity())
                .basePrice(paymentApprovedEvent.getBasePrice())
                .totalTax(paymentApprovedEvent.getTotalTax())
                .build();

        commandGateway.send(createShipmentCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Start the compensating transaction
                String errorMessage = commandResultMessage.exceptionResult().getMessage();
                log.error("PaymentProcessedEvent unable to process shipment due to {}", errorMessage);
                CompensateOrder compensateOrder = new CompensateOrder();
                compensateOrder.setOrderId(paymentApprovedEvent.getOrderId());
                compensateOrder.setProductId(paymentApprovedEvent.getProductId());
                compensateOrder.setUserId(paymentApprovedEvent.getUser().getUserId());
                compensateOrder.setPaymentId(paymentApprovedEvent.getPaymentId());
                compensateOrder.setShipmentId(createShipmentCommand.getShipmentId());
                compensateOrder.setReasonToFailed(simplifyErrorMessage(commandResultMessage,
                        OderFailure.SHIPMENT_SERVICE_NOT_AVAILABLE));

                orderRejectedCommand(compensateOrder);
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentCancelledEvent paymentCancelledEvent) {
        log.info("Payment is not approved!");
        // Start a compensating transaction
        CancelProductReserveCommand cancelProductReserveCommand = CancelProductReserveCommand.builder()
                .orderId(paymentCancelledEvent.getOrderId())
                .productId(paymentCancelledEvent.getProductId())
                .quantity(paymentCancelledEvent.getQuantity())
                .userId(paymentCancelledEvent.getUserId())
                .reasonToFailed(paymentCancelledEvent.getReasonToFailed())
                .build();
        commandGateway.send(cancelProductReserveCommand);

        CompensateOrder compensateOrder = new CompensateOrder();
        compensateOrder.setOrderId(paymentCancelledEvent.getOrderId());
        compensateOrder.setProductId(paymentCancelledEvent.getProductId());
        compensateOrder.setPaymentId(paymentCancelledEvent.getPaymentId());
        compensateOrder.setUserId(paymentCancelledEvent.getUserId());
        compensateOrder.setReasonToFailed(paymentCancelledEvent.getReasonToFailed());

        orderRejectedCommand(compensateOrder);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent orderShippedEvent) {
        log.info("OrderShippedEvent started for order approval");
        ApproveOrderCommand approveOrderCommand = ApproveOrderCommand.builder()
                .orderId(orderShippedEvent.getOrderId())
                .paymentId(orderShippedEvent.getPaymentId())
                .shipmentId(orderShippedEvent.getShipmentId())
                .orderStatus(OrderStatus.ORDER_PLACED)
                .build();
        commandGateway.send(approveOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ShipmentCancelledEvent shipmentCancelledEvent) {
        log.info("Start a compensating transaction from Shipment");
        // Start a compensating transaction
        CancelPaymentCommand cancelPaymentCommand = CancelPaymentCommand.builder()
                .paymentId(shipmentCancelledEvent.getPaymentId())
                .orderId(shipmentCancelledEvent.getOrderId())
                .productId(shipmentCancelledEvent.getProductId())
                .userId(shipmentCancelledEvent.getUserId())
                .reasonToFailed(shipmentCancelledEvent.getReasonToFailed())
                .quantity(shipmentCancelledEvent.getQuantity())
                .price(shipmentCancelledEvent.getPrice())
                .build();
        commandGateway.send(cancelPaymentCommand);

        CancelProductReserveCommand cancelProductReserveCommand = CancelProductReserveCommand.builder()
                .productId(shipmentCancelledEvent.getProductId())
                .orderId(shipmentCancelledEvent.getOrderId())
                .userId(shipmentCancelledEvent.getUserId())
                .quantity(shipmentCancelledEvent.getQuantity())
                .reasonToFailed(shipmentCancelledEvent.getReasonToFailed())
                .build();
        commandGateway.send(cancelProductReserveCommand);

        CompensateOrder compensateOrder = new CompensateOrder();
        compensateOrder.setOrderId(shipmentCancelledEvent.getOrderId());
        compensateOrder.setProductId(shipmentCancelledEvent.getProductId());
        compensateOrder.setPaymentId(shipmentCancelledEvent.getPaymentId());
        compensateOrder.setShipmentId(shipmentCancelledEvent.getShipmentId());
        compensateOrder.setUserId(shipmentCancelledEvent.getUserId());
        compensateOrder.setReasonToFailed(shipmentCancelledEvent.getReasonToFailed());

        orderRejectedCommand(compensateOrder);
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderApprovedEvent event) {
        log.info("Order is approved. Order Saga is complete for orderId: " + event.getOrderId());
        OrderSummary orderSummary = OrderSummary.builder()
                .orderId(event.getOrderId())
                .orderStatus(event.getOrderStatus().toString())
                .message("Thank you for your order! We’ll let you know as soon as it ships. " +
                        "You can track your order here,review us here, or shop again here.")
                .build();
        queryUpdateEmitter.emit(FindOrderQuery.class, query -> true, orderSummary);
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCancelledEvent event) {
        log.info("Order is cancelled for orderId: " + event.getOrderId());
        OrderSummary orderSummary = OrderSummary.builder()
                .orderId(event.getOrderId())
                .orderStatus(event.getOrderStatus().toString())
                .message(event.getReasonToFailed())
                .build();
        queryUpdateEmitter.emit(FindOrderQuery.class, query -> true, orderSummary);
    }

    private void orderRejectedCommand(CompensateOrder compensateOrder) {
        RejectOrderCommand rejectOrderCommand = RejectOrderCommand.builder()
                .orderId(compensateOrder.getOrderId())
                .userId(compensateOrder.getUserId())
                .reasonToFailed(compensateOrder.getReasonToFailed())
                .paymentId(compensateOrder.getPaymentId())
                .shipmentId(compensateOrder.getShipmentId())
                .orderStatus(OrderStatus.ORDER_NOT_APPROVED)
                .productId(compensateOrder.getProductId())
                .build();
        commandGateway.send(rejectOrderCommand);
    }

    @SuppressWarnings("java:S3740")
    private String simplifyErrorMessage(CommandResultMessage commandResultMessage, OderFailure errorMessage) {
        return commandResultMessage.exceptionResult().getMessage() != null
                && commandResultMessage.exceptionResult().getMessage().contains("No Handler for command:")?
                errorMessage.toString():commandResultMessage.exceptionResult().getMessage();
    }
}
