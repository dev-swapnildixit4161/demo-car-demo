package com.nashtech.shipment.aggregate;

import com.nashtech.common.command.CreateShipmentCommand;
import com.nashtech.common.event.OrderShippedEvent;
import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.shipment.util.ObjectCreator;
import com.nashtech.shipment.util.TestTag;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag(TestTag.SMALL_TESTS)
class ShipmentAggregateTest {
    private AggregateTestFixture<ShipmentAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(ShipmentAggregate.class);
    }

    @Test
    void testShipmentAggregateCreation() {

        CreateShipmentCommand createShipmentCommand = ObjectCreator.getShipmentCommand();

        ShipmentCreatedEvent shipmentCreatedEvent = ObjectCreator.getShipmentCreatedEvent();

        OrderShippedEvent shippedEvent = ObjectCreator.getOrderShippedEvent();

        fixture.givenNoPriorActivity()
                .when(createShipmentCommand)
                .expectEvents(shipmentCreatedEvent, shippedEvent);
    }


    @Test
    void test_shipment_created_event_handling_with_null_values() {
        ShipmentAggregate shipmentAggregate = new ShipmentAggregate();
        ShipmentCreatedEvent shipmentCreatedEvent = ShipmentCreatedEvent.builder().build();

        shipmentAggregate.on(shipmentCreatedEvent);

        assertNull(shipmentAggregate.getShipmentId());
        assertNull(shipmentAggregate.getOrderId());
        assertNull(shipmentAggregate.getPaymentId());
        assertNull(shipmentAggregate.getProductId());
        assertNull(shipmentAggregate.getBrand());
        assertNull(shipmentAggregate.getQuantity());
        assertNull(shipmentAggregate.getBasePrice());
        assertNull(shipmentAggregate.getSubTotal());
        assertNull(shipmentAggregate.getTotal());
        assertNull(shipmentAggregate.getTax());
        assertNull(shipmentAggregate.getTotalTax());
        assertNull(shipmentAggregate.getUser());
    }

    @Test
    void test_shipment_aggregate_state_update() {

        ShipmentCreatedEvent shipmentCreatedEvent = ShipmentCreatedEvent.builder()
                .shipmentId("shipmentId")
                .orderId("orderId")
                .paymentId("paymentId")
                .productId("productId")
                .quantity(10)
                .brand("brand")
                .basePrice(100.0)
                .tax(0.1f)
                .totalTax(10.0f)
                .subTotal(1000.0)
                .total(1100.0)
                .user(ObjectCreator.getUser())
                .build();
        ShipmentAggregate shipmentAggregate = new ShipmentAggregate();
        shipmentAggregate.on(shipmentCreatedEvent);
        assertEquals(shipmentCreatedEvent.getShipmentId(), shipmentAggregate.getShipmentId());
        assertEquals(shipmentCreatedEvent.getOrderId(), shipmentAggregate.getOrderId());
        assertEquals(shipmentCreatedEvent.getPaymentId(), shipmentAggregate.getPaymentId());
        assertEquals(shipmentCreatedEvent.getProductId(), shipmentAggregate.getProductId());
        assertEquals(shipmentCreatedEvent.getQuantity(), shipmentAggregate.getQuantity());
        assertEquals(shipmentCreatedEvent.getBrand(), shipmentAggregate.getBrand());
        assertEquals(shipmentCreatedEvent.getBasePrice(), shipmentAggregate.getBasePrice());
        assertEquals(shipmentCreatedEvent.getTax(), shipmentAggregate.getTax());
        assertEquals(shipmentCreatedEvent.getTotalTax(), shipmentAggregate.getTotalTax());
        assertEquals(shipmentCreatedEvent.getSubTotal(), shipmentAggregate.getSubTotal());
        assertEquals(shipmentCreatedEvent.getTotal(), shipmentAggregate.getTotal());
        assertEquals(shipmentCreatedEvent.getUser(), shipmentAggregate.getUser());
    }

    @Test
    void testShipmentCreatedEventHandling() {

        ShipmentAggregate shipmentAggregate = new ShipmentAggregate();
        ShipmentCreatedEvent shipmentCreatedEvent = ObjectCreator.getShipmentCreatedEvent();
        shipmentAggregate.on(shipmentCreatedEvent);
        assertEquals(shipmentCreatedEvent.getShipmentId(), shipmentAggregate.getShipmentId());
        assertEquals(shipmentCreatedEvent.getOrderId(), shipmentAggregate.getOrderId());
        assertEquals(shipmentCreatedEvent.getPaymentId(), shipmentAggregate.getPaymentId());
        assertEquals(shipmentCreatedEvent.getProductId(), shipmentAggregate.getProductId());
        assertEquals(shipmentCreatedEvent.getBrand(), shipmentAggregate.getBrand());
        assertEquals(shipmentCreatedEvent.getQuantity(), shipmentAggregate.getQuantity());
        assertEquals(shipmentCreatedEvent.getBasePrice(), shipmentAggregate.getBasePrice());
        assertEquals(shipmentCreatedEvent.getSubTotal(), shipmentAggregate.getSubTotal());
        assertEquals(shipmentCreatedEvent.getTotal(), shipmentAggregate.getTotal());
        assertEquals(shipmentCreatedEvent.getTax(), shipmentAggregate.getTax());
        assertEquals(shipmentCreatedEvent.getTotalTax(), shipmentAggregate.getTotalTax());
        assertEquals(shipmentCreatedEvent.getUser(), shipmentAggregate.getUser());
    }

    @Test
    void test_negative_totalTax_throws_exception() {
        CreateShipmentCommand createShipmentCommand = CreateShipmentCommand.builder()
                .shipmentId("shipmentId")
                .orderId("orderId")
                .paymentId("paymentId")
                .productId("productId")
                .quantity(10)
                .brand("brand")
                .basePrice(100.0)
                .tax(0.1f)
                .totalTax(-10.0f)
                .subTotal(1000.0)
                .total(1100.0)
                .user(ObjectCreator.getUser())
                .build();

        assertThrows(IllegalStateException.class, () -> {
            new ShipmentAggregate(createShipmentCommand);
        });
    }


    @Test
    void testShipmentCreatedEventHandlingWithNullValues() {

        ShipmentAggregate shipmentAggregate = new ShipmentAggregate();
        ShipmentCreatedEvent shipmentCreatedEvent = ShipmentCreatedEvent.builder().build();

        shipmentAggregate.on(shipmentCreatedEvent);

        assertNull(shipmentAggregate.getShipmentId());
        assertNull(shipmentAggregate.getOrderId());
        assertNull(shipmentAggregate.getPaymentId());
        assertNull(shipmentAggregate.getProductId());
        assertNull(shipmentAggregate.getBrand());
        assertNull(shipmentAggregate.getQuantity());
        assertNull(shipmentAggregate.getBasePrice());
        assertNull(shipmentAggregate.getSubTotal());
        assertNull(shipmentAggregate.getTotal());
        assertNull(shipmentAggregate.getTax());
        assertNull(shipmentAggregate.getTotalTax());
        assertNull(shipmentAggregate.getUser());
    }

    @Test
    void test_instantiation_success() {
        ShipmentAggregate shipmentAggregate = new ShipmentAggregate();
        assertNotNull(shipmentAggregate);
    }


    @Test
    void testShipmentAggregateConstructorWithNullShipmentId() {
        CreateShipmentCommand createShipmentCommand = CreateShipmentCommand.builder()
                .shipmentId(null)
                .orderId("orderId")
                .paymentId("paymentId")
                .productId("productId")
                .quantity(10)
                .brand("brand")
                .basePrice(100.0)
                .tax(0.1f)
                .totalTax(10.0f)
                .subTotal(1000.0)
                .total(1100.0)
                .user(ObjectCreator.getUser())
                .build();

        assertThrows(IllegalStateException.class, () -> {
            new ShipmentAggregate(createShipmentCommand);
        });
    }

    @Test
    void testShipmentAggregateConstructorWithInvalidTotalCalculation() {
        CreateShipmentCommand createShipmentCommand = CreateShipmentCommand.builder()
                .shipmentId("shipmentId")
                .orderId("orderId")
                .paymentId("paymentId")
                .productId("productId")
                .quantity(-1) // Invalid quantity
                .brand("brand")
                .basePrice(100.0)
                .tax(0.1f)
                .totalTax(10.0f)
                .subTotal(1000.0)
                .total(1100.0)
                .user(ObjectCreator.getUser())
                .build();

        assertThrows(IllegalStateException.class, () -> {
            new ShipmentAggregate(createShipmentCommand);
        });
    }

}