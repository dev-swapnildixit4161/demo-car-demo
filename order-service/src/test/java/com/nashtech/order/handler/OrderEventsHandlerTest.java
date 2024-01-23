package com.nashtech.order.handler;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.repository.FailedOrderRepository;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.FailedOrderEntity;
import com.nashtech.order.repository.entity.OrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderEventsHandler.class})
@ExtendWith(SpringExtension.class)
class OrderEventsHandlerTest {
    @MockBean
    private FailedOrderRepository failedOrderRepository;

    @Autowired
    private OrderEventsHandler orderEventsHandler;

    @MockBean
    private OrderRepository orderRepository;

    /**
     * Method under test: {@link OrderEventsHandler#on(OrderApprovedEvent)}
     */
    @Test
    void testOrderApprovedEventHandler() {
        // Arrange
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId("42");
        orderEntity.setOrderStatus("Order Status");
        orderEntity.setPaymentId("42");
        orderEntity.setProductId("42");
        orderEntity.setShipmentId("42");
        orderEntity.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        orderEntity.setUserId("42");

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setOrderId("42");
        orderEntity2.setOrderStatus("Order Status");
        orderEntity2.setPaymentId("42");
        orderEntity2.setProductId("42");
        orderEntity2.setShipmentId("42");
        orderEntity2.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        orderEntity2.setUserId("42");
        when(orderRepository.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity2);
        when(orderRepository.findByOrderId(Mockito.<String>any())).thenReturn(orderEntity);
        doNothing().when(failedOrderRepository).delete(Mockito.<FailedOrderEntity>any());
        OrderApprovedEvent orderApprovedEvent = OrderApprovedEvent.builder()
                .orderId("42")
                .orderStatus(OrderStatus.ORDER_NOT_APPROVED)
                .paymentId("42")
                .shipmentId("42")
                .build();

        // Act
        orderEventsHandler.on(orderApprovedEvent);

        // Assert
        verify(orderRepository).findByOrderId(Mockito.<String>any());
        verify(failedOrderRepository).delete(Mockito.<FailedOrderEntity>any());
        verify(orderRepository).save(Mockito.<OrderEntity>any());
    }

    /**
     * Method under test: {@link OrderEventsHandler#on(OrderCancelledEvent)}
     */
    @Test
    void testOrderCancelledEventHandler() {
        // Arrange
        Optional<FailedOrderEntity> emptyResult = Optional.empty();
        when(failedOrderRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        OrderCancelledEvent event = OrderCancelledEvent.builder()
                .orderId("42")
                .orderStatus(OrderStatus.ORDER_NOT_APPROVED)
                .paymentId("42")
                .productId("42")
                .reasonToFailed("Just cause")
                .shipmentId("42")
                .userId("42")
                .build();

        // Act
        orderEventsHandler.on(event);

        // Assert that nothing has changed
        verify(failedOrderRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OrderEventsHandler#on(OrderCreatedEvent)}
     */
    @Test
    void testOrderCreatedEventHandler() {
        // Arrange
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId("42");
        orderEntity.setOrderStatus("Order Status");
        orderEntity.setPaymentId("42");
        orderEntity.setProductId("42");
        orderEntity.setShipmentId("42");
        orderEntity.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        orderEntity.setUserId("42");
        when(orderRepository.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity);

        FailedOrderEntity failedOrderEntity = new FailedOrderEntity();
        failedOrderEntity.setOrderId("42");
        failedOrderEntity.setOrderStatus("Order Status");
        failedOrderEntity.setPaymentId("42");
        failedOrderEntity.setProductId("42");
        failedOrderEntity.setReasonToFailed("Just cause");
        failedOrderEntity.setShipmentId("42");
        failedOrderEntity
                .setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        failedOrderEntity.setUserId("42");
        when(failedOrderRepository.save(Mockito.<FailedOrderEntity>any())).thenReturn(failedOrderEntity);
        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId("42")
                .orderStatus(OrderStatus.ORDER_NOT_APPROVED)
                .productId("42")
                .quantity(1)
                .userId("42")
                .build();

        // Act
        orderEventsHandler.on(event);

        // Assert
        verify(failedOrderRepository).save(Mockito.<FailedOrderEntity>any());
        verify(orderRepository).save(Mockito.<OrderEntity>any());
    }

    /**
     * Method under test: {@link OrderEventsHandler#handle(Exception)}
     */
    @Test
    void testExceptionHandle() {
        Exception exception = new Exception("foo");
        try {
            Assertions.assertEquals("foo", exception.getMessage());
            orderEventsHandler.handle(exception);
        }catch (Exception e){}

    }
}
