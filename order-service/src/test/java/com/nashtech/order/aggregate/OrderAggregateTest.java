package com.nashtech.order.aggregate;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.commands.ApproveOrderCommand;
import com.nashtech.order.commands.CreateOrderCommand;
import com.nashtech.order.commands.RejectOrderCommand;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class OrderAggregateTest {

    private static final String ORDER_ID = UUID.randomUUID().toString();
    private static final String PRODUCT_ID = UUID.randomUUID().toString();
    private static final String PAYMENT_ID = UUID.randomUUID().toString();
    private static final String SHIPMENT_ID = UUID.randomUUID().toString();
    private static final String USER_ID = "1652";

    private FixtureConfiguration<OrderAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    private static final OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
            .orderId(ORDER_ID)
            .productId(PRODUCT_ID)
            .quantity(2)
            .userId(USER_ID)
            .orderStatus(OrderStatus.ORDER_PARTIALLY_APPROVED)
            .build();

    @Test
    public void testCreateOrderCommand() {
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .productId(PRODUCT_ID)
                .userId(USER_ID)
                .quantity(2)
                .orderId(ORDER_ID)
                .build();

        fixture.givenNoPriorActivity()
                .when(createOrderCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(orderCreatedEvent);
    }

    @Test
    public void testApproveOrderCommand() {
        ApproveOrderCommand approveOrderCommand = ApproveOrderCommand.builder()
                .orderId(ORDER_ID)
                .paymentId(PAYMENT_ID)
                .shipmentId(SHIPMENT_ID)
                .orderStatus(OrderStatus.ORDER_PLACED)
                .build();

        OrderApprovedEvent orderApprovedEvent = OrderApprovedEvent.builder()
                .orderId(approveOrderCommand.getOrderId())
                .paymentId(approveOrderCommand.getPaymentId())
                .shipmentId(approveOrderCommand.getShipmentId())
                .orderStatus(approveOrderCommand.getOrderStatus())
                .build();

        fixture.given(orderCreatedEvent)
                .when(approveOrderCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(orderApprovedEvent);
    }

    @Test
    public void testRejectOrderCommand() {
        RejectOrderCommand rejectOrderCommand = RejectOrderCommand.builder()
                .userId(USER_ID)
                .orderId(ORDER_ID)
                .productId(PRODUCT_ID)
                .paymentId(PAYMENT_ID)
                .reasonToFailed("The user has insufficient amounts.")
                .orderStatus(OrderStatus.ORDER_NOT_APPROVED)
                .build();

        OrderCancelledEvent orderCancelledEvent = OrderCancelledEvent.builder()
                .orderId(rejectOrderCommand.getOrderId())
                .productId(rejectOrderCommand.getProductId())
                .paymentId(rejectOrderCommand.getPaymentId())
                .shipmentId(rejectOrderCommand.getShipmentId())
                .userId(rejectOrderCommand.getUserId())
                .reasonToFailed(rejectOrderCommand.getReasonToFailed())
                .orderStatus(rejectOrderCommand.getOrderStatus())
                .build();

        fixture.given(orderCreatedEvent)
                .when(rejectOrderCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(orderCancelledEvent);
    }
}
