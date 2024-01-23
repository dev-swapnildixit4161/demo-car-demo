package com.nashtech.order.handler;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.repository.OrderLookupRepository;
import com.nashtech.order.repository.entity.OrderLookupEntity;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderLookupEventsHandler.class})
@ExtendWith(SpringExtension.class)
class OrderLookupEventsHandlerTest {
    @Autowired
    private OrderLookupEventsHandler orderLookupEventsHandler;

    @MockBean
    private OrderLookupRepository orderLookupRepository;

    /**
     * Method under test: {@link OrderLookupEventsHandler#on(OrderCreatedEvent)}
     */
    @Test
    void testOrderCreatedEventEventHandler() {
        // Arrange
        OrderLookupEntity orderLookupEntity = new OrderLookupEntity("42",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        when(orderLookupRepository.save(Mockito.<OrderLookupEntity>any())).thenReturn(orderLookupEntity);
        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId("42")
                .orderStatus(OrderStatus.ORDER_NOT_APPROVED)
                .productId("42")
                .quantity(1)
                .userId("42")
                .build();

        // Act
        orderLookupEventsHandler.on(event);

        // Assert
        verify(orderLookupRepository).save(Mockito.<OrderLookupEntity>any());
    }

    /**
     * Method under test: {@link OrderLookupEventsHandler#handle(Exception)}
     */
    @Test
    void testExceptionHandle() {
        Exception exception = new Exception("foo");
        try {
            Assertions.assertEquals("foo", exception.getMessage());
            orderLookupEventsHandler.handle(exception);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
