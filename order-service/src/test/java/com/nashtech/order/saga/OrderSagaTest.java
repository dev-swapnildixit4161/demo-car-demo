package com.nashtech.order.saga;

import com.nashtech.common.event.*;
import com.nashtech.common.model.ShipmentStatus;
import com.nashtech.common.model.User;
import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderSaga.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderSagaTest {
  @MockBean
  private CommandGateway commandGateway;

  @Autowired
  private OrderSaga orderSaga;


  @MockBean
  private QueryUpdateEmitter queryUpdateEmitter;

  /**
   * Method under test: {@link OrderSaga#handle(OrderShippedEvent)}
   */
  @Test
  public void testOrderShippedEventHandlerSaga() {
    // Arrange
    when(commandGateway.send(Mockito.<Object>any())).thenReturn(new CompletableFuture<>());
    OrderShippedEvent orderShippedEvent = OrderShippedEvent.builder()
            .orderId("42")
            .paymentId("42")
            .shipmentId("42")
            .build();

    // Act
    orderSaga.handle(orderShippedEvent);

    // Assert
    verify(commandGateway).send(Mockito.<Object>any());
  }

  /**
   * Method under test: {@link OrderSaga#handle(PaymentApprovedEvent)}
   */
  @Test
  public void testPaymentApprovedEventHandlerSaga() {
    // Arrange
    doNothing().when(commandGateway).send(Mockito.<Object>any(), Mockito.<CommandCallback<Object, Object>>any());
    PaymentApprovedEvent.PaymentApprovedEventBuilder totalTaxResult = PaymentApprovedEvent.builder()
            .basePrice(10.0d)
            .brand("Brand")
            .orderId("42")
            .paymentId("42")
            .productId("42")
            .quantity(1)
            .subTotal(10.0d)
            .tax(10.0f)
            .total(10.0d)
            .totalTax(10.0f);
    User user = User.builder().emailId("42").firstName("Jane").lastName("Doe").mobileNumber("42").userId("42").build();
    PaymentApprovedEvent paymentApprovedEvent = totalTaxResult.user(user).build();

    // Act
    orderSaga.handle(paymentApprovedEvent);

    // Assert
    verify(commandGateway).send(Mockito.<Object>any(), Mockito.<CommandCallback<Object, Object>>any());
  }

  /**
   * Method under test: {@link OrderSaga#handle(PaymentCancelledEvent)}
   */
  @Test
  public void testPaymentCancelledEventHandlerSaga() {
    // Arrange
    when(commandGateway.send(Mockito.<Object>any())).thenReturn(new CompletableFuture<>());
    PaymentCancelledEvent paymentCancelledEvent = PaymentCancelledEvent.builder()
            .orderId("42")
            .paymentId("42")
            .productId("42")
            .quantity(1)
            .reasonToFailed("Just cause")
            .userId("42")
            .build();

    // Act
    orderSaga.handle(paymentCancelledEvent);

    // Assert
    verify(commandGateway, atLeast(1)).send(Mockito.<Object>any());
  }

  /**
   * Method under test: {@link OrderSaga#handle(ProductReserveFailedEvent)}
   */
  @Test
  public void testProductReserveFailedEventHandlerSaga() {
    // Arrange
    when(commandGateway.send(Mockito.<Object>any())).thenReturn(new CompletableFuture<>());
    ProductReserveFailedEvent productReserveFailedEvent = ProductReserveFailedEvent.builder()
            .orderId("42")
            .productId("42")
            .reasonToFailed("Just cause")
            .userId("42")
            .build();

    // Act
    orderSaga.handle(productReserveFailedEvent);

    // Assert
    verify(commandGateway).send(Mockito.<Object>any());
  }

  /**
   * Method under test: {@link OrderSaga#handle(ProductReservedEvent)}
   */
  @Test
  public void testProductReservedEventHandlerSaga() {
    // Arrange
    doNothing().when(commandGateway).send(Mockito.<Object>any(), Mockito.<CommandCallback<Object, Object>>any());
    ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
            .basePrice(10.0d)
            .brand("Brand")
            .color("Color")
            .mileage(10.0d)
            .model("Model")
            .orderId("42")
            .productId("42")
            .quantity(1)
            .subTotal(10.0d)
            .tax(10.0f)
            .total(10.0d)
            .totalTax(10.0f)
            .userId("42")
            .year(1)
            .build();

    // Act
    orderSaga.handle(productReservedEvent);

    // Assert
    verify(commandGateway).send(Mockito.<Object>any(), Mockito.<CommandCallback<Object, Object>>any());
  }

  /**
   * Method under test: {@link OrderSaga#handle(ShipmentCancelledEvent)}
   */
  @Test
  public void testShipmentCancelledEventHandlerSaga() {
    // Arrange
    when(commandGateway.send(Mockito.<Object>any())).thenReturn(new CompletableFuture<>());
    ShipmentCancelledEvent shipmentCancelledEvent = ShipmentCancelledEvent.builder()
            .firstName("Jane")
            .grandTotal(10.0d)
            .lastName("Doe")
            .orderId("42")
            .paymentId("42")
            .price(10.0d)
            .productId("42")
            .quantity(1)
            .reasonToFailed("Just cause")
            .shipmentId("42")
            .shipmentStatus(ShipmentStatus.SHIPMENT_CREATED)
            .subTotal(10.0d)
            .tax(10.0f)
            .userId("42")
            .build();

    // Act
    orderSaga.handle(shipmentCancelledEvent);

    // Assert
    verify(commandGateway, atLeast(1)).send(Mockito.<Object>any());
  }

  /**
   * Method under test: {@link OrderSaga#handle(OrderApprovedEvent)}
   */
  @Test
  public void testOrderApprovedEventHandlerSaga() {
    // Arrange
    doNothing().when(queryUpdateEmitter)
            .emit(Mockito.<Class<Object>>any(), Mockito.<Predicate<Object>>any(), Mockito.<Object>any());
    OrderApprovedEvent event = OrderApprovedEvent.builder()
            .orderId("42")
            .orderStatus(OrderStatus.ORDER_NOT_APPROVED)
            .paymentId("42")
            .shipmentId("42")
            .build();

    // Act
    orderSaga.handle(event);

    // Assert
    verify(queryUpdateEmitter).emit(Mockito.<Class<Object>>any(), Mockito.<Predicate<Object>>any(),
            Mockito.<Object>any());
  }

  /**
   * Method under test: {@link OrderSaga#handle(OrderCancelledEvent)}
   */
  @Test
  public void testOrderCancelledEventHandlerSaga() {
    // Arrange
    doNothing().when(queryUpdateEmitter)
            .emit(Mockito.<Class<Object>>any(), Mockito.<Predicate<Object>>any(), Mockito.<Object>any());
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
    orderSaga.handle(event);

    // Assert
    verify(queryUpdateEmitter).emit(Mockito.<Class<Object>>any(), Mockito.<Predicate<Object>>any(),
            Mockito.<Object>any());
  }

  /**
   * Method under test: {@link OrderSaga#handle(OrderCreatedEvent)}
   */
  @Test
  public void testOrderCreatedEventHandlerSaga() {
    // Arrange
    doNothing().when(commandGateway).send(Mockito.<Object>any(), Mockito.<CommandCallback<Object, Object>>any());
    OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
            .orderId("42")
            .orderStatus(OrderStatus.ORDER_PLACED)
            .productId("42")
            .quantity(1)
            .userId("42")
            .build();

    // Act
    orderSaga.handle(orderCreatedEvent);

    // Assert
    verify(commandGateway).send(Mockito.<Object>any(), Mockito.<CommandCallback<Object, Object>>any());
  }
}
