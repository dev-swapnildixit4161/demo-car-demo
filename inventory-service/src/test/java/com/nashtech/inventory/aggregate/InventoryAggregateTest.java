package com.nashtech.inventory.aggregate;

import com.nashtech.common.command.CancelProductReserveCommand;
import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReserveFailedEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.command.CreateProductCommand;
import com.nashtech.inventory.events.ProductCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for the {@link InventoryAggregate} class.
 */
class InventoryAggregateTest {
    @Autowired
    private AggregateTestFixture<InventoryAggregate> fixture;

    /**
     * Set up the test fixture before each test method.
     */
    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(InventoryAggregate.class);
    }

    /**
     * Test the handling of a {@link ProductCreatedEvent} with null values.
     */
    @Test
    void test_product_created_event_handling_with_null_values() {
        InventoryAggregate inventoryAggregate = new InventoryAggregate();
        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder().build();

        inventoryAggregate.on(productCreatedEvent);

        assertNull(inventoryAggregate.getProductId());
        assertNull(inventoryAggregate.getBrand());
        assertNull(inventoryAggregate.getModel());
        assertNull(inventoryAggregate.getYear());
        assertNull(inventoryAggregate.getColor());
        assertNull(inventoryAggregate.getMileage());
        assertNull(inventoryAggregate.getBasePrice());
        assertNull(inventoryAggregate.getTax());
        assertNull(inventoryAggregate.getQuantity());
    }

    /**
     * Test the update of aggregate state after handling a {@link ProductCreatedEvent}.
     */
    @Test
    void test_product_aggregate_state_update() {
        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .productId("productId")
                .brand("brand")
                .model("model")
                .year(2022)
                .color("color")
                .mileage(10000.0)
                .basePrice(100.0)
                .tax(0.1f)
                .quantity(10)
                .build();

        InventoryAggregate inventoryAggregate = new InventoryAggregate();
        inventoryAggregate.on(productCreatedEvent);

        assertEquals(productCreatedEvent.getProductId(), inventoryAggregate.getProductId());
        assertEquals(productCreatedEvent.getBrand(), inventoryAggregate.getBrand());
        assertEquals(productCreatedEvent.getModel(), inventoryAggregate.getModel());
        assertEquals(productCreatedEvent.getYear(), inventoryAggregate.getYear());
        assertEquals(productCreatedEvent.getColor(), inventoryAggregate.getColor());
        assertEquals(productCreatedEvent.getMileage(), inventoryAggregate.getMileage());
        assertEquals(productCreatedEvent.getBasePrice(), inventoryAggregate.getBasePrice());
        assertEquals(productCreatedEvent.getTax(), inventoryAggregate.getTax());
        assertEquals(productCreatedEvent.getQuantity(), inventoryAggregate.getQuantity());
    }

    /**
     * Test the instantiation of the {@link InventoryAggregate} class.
     */
    @Test
    void test_instantiation_success() {
        InventoryAggregate inventoryAggregate = new InventoryAggregate();
        assertNotNull(inventoryAggregate);
    }

    /**
     * Test the handling of a {@link CreateProductCommand}.
     */
    @Test
    void test_create_product_command_handling() {
        String productId = "productId";
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(productId)
                .brand("brand")
                .model("model")
                .year(2022)
                .color("color")
                .mileage(10000.0)
                .basePrice(100.0)
                .tax(0.1f)
                .quantity(10)
                .build();

        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .productId(productId)
                .brand("brand")
                .model("model")
                .year(2022)
                .color("color")
                .mileage(10000.0)
                .basePrice(100.0)
                .quantity(10)
                .tax(0.1f)
                .build();

        fixture.givenNoPriorActivity()
                .when(createProductCommand)
                .expectEvents(productCreatedEvent);
    }

    /**
     * Test the validation of the brand field in a {@link CreateProductCommand}.
     */
    @Test
    void test_create_product_command_brand_validation() {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("productId")
                .brand("")
                .model("model")
                .year(2022)
                .color("color")
                .mileage(10000.0)
                .basePrice(100.0)
                .tax(0.1f)
                .quantity(10)
                .build();
        fixture.givenNoPriorActivity()
                .when(createProductCommand)
                .expectException(IllegalArgumentException.class)
                .expectExceptionMessage("Brand cannot be empty");
    }

    /**
     * Test for handling failure of reserving a product using the ReserveProductCommand.
     * This test covers the scenario where the requested quantity is more than the available quantity.
     */
    @Test
    void test_reserve_product_command_handling_failure() {
        String productId = "productId";

        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .productId(productId)
                .brand("brand")
                .model("model")
                .year(2022)
                .color("color")
                .mileage(10000.0)
                .basePrice(100.0)
                .tax(0.1f)
                .quantity(5)
                .build();

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(productId)
                .userId("userId")
                .orderId("orderId")
                .quantity(10)
                .build();

        ProductReserveFailedEvent productReserveFailedEvent = ProductReserveFailedEvent.builder()
                .productId(reserveProductCommand.getProductId())
                .userId(reserveProductCommand.getUserId())
                .orderId(reserveProductCommand.getOrderId())
                .reasonToFailed("Insufficient number of items in stock for product " + reserveProductCommand.getProductId())
                .build();

        AggregateTestFixture<InventoryAggregate> fixture = new AggregateTestFixture<>(InventoryAggregate.class);
        fixture.given(productCreatedEvent)
                .when(reserveProductCommand)
                .expectEvents(productReserveFailedEvent)
                .expectSuccessfulHandlerExecution();
    }

    /**
     * Test the handling of a successful reservation using the {@link ReserveProductCommand}.
     */
    @Test
    void test_reserve_product_command_handling_success() {
        String productId = "productId";

        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .productId(productId)
                .brand("brand")
                .model("model")
                .year(2022)
                .color("color")
                .mileage(10000.0)
                .basePrice(100.0)
                .tax(0.1f)
                .quantity(10)
                .build();

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(productId)
                .userId("userId")
                .orderId("orderId")
                .quantity(5)
                .build();

        double expectedSubTotal = reserveProductCommand.getQuantity() * productCreatedEvent.getBasePrice();
        float expectedTotalTax = reserveProductCommand.getQuantity() * productCreatedEvent.getTax();
        double expectedTotal = expectedSubTotal + expectedTotalTax;

        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .orderId(reserveProductCommand.getOrderId())
                .productId(reserveProductCommand.getProductId())
                .userId(reserveProductCommand.getUserId())
                .quantity(reserveProductCommand.getQuantity())
                .brand(productCreatedEvent.getBrand())
                .basePrice(productCreatedEvent.getBasePrice())
                .tax(productCreatedEvent.getTax())
                .totalTax(expectedTotalTax)
                .subTotal(expectedSubTotal)
                .total(expectedTotal)
                .model(productCreatedEvent.getModel())
                .mileage(productCreatedEvent.getMileage())
                .color(productCreatedEvent.getColor())
                .year(productCreatedEvent.getYear())
                .build();

        AggregateTestFixture<InventoryAggregate> fixture = new AggregateTestFixture<>(InventoryAggregate.class);
        fixture.given(productCreatedEvent)
                .when(reserveProductCommand)
                .expectEvents(productReservedEvent)
                .expectSuccessfulHandlerExecution();
    }

    /**
     * Test case to verify the handling of a reserve product command when there is insufficient quantity in stock.
     */
    @Test
    void test_reserve_product_command_handling_insufficient_quantity() {
        String productId = "productId";

        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .productId(productId)
                .brand("brand")
                .model("model")
                .year(2022)
                .color("color")
                .mileage(10000.0)
                .basePrice(100.0)
                .tax(0.1f)
                .quantity(2)
                .build();

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(productId)
                .userId("userId")
                .orderId("orderId")
                .quantity(5)
                .build();

        AggregateTestFixture<InventoryAggregate> fixture = new AggregateTestFixture<>(InventoryAggregate.class);
        fixture.given(productCreatedEvent)
                .when(reserveProductCommand)
                .expectEvents(ProductReserveFailedEvent.builder()
                        .productId(productId)
                        .userId("userId")
                        .orderId("orderId")
                        .reasonToFailed("Insufficient number of items in stock for product " + productId)
                        .build())
                .expectState(state -> {
                    assertEquals(productId, state.getProductId());
                    assertEquals("brand", state.getBrand());
                    assertEquals(2, state.getQuantity()); // Check that quantity is not reduced
                });
    }

    /**
     * Test case to verify the handling of a cancel product reserve command.
     */
    @Test
    void test_cancel_product_reserve_command_handling() {
        String productId = "productId";

        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .productId(productId)
                .brand("brand")
                .model("model")
                .year(2022)
                .color("color")
                .mileage(10000.0)
                .basePrice(100.0)
                .tax(0.1f)
                .quantity(5)
                .build();

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(productId)
                .userId("userId")
                .orderId("orderId")
                .quantity(3)
                .build();

        CancelProductReserveCommand cancelProductReserveCommand = CancelProductReserveCommand.builder()
                .productId(productId)
                .userId("userId")
                .orderId("orderId")
                .quantity(2)
                .build();

        AggregateTestFixture<InventoryAggregate> fixture = new AggregateTestFixture<>(InventoryAggregate.class);
        fixture.given(productCreatedEvent, reserveProductCommand)
                .when(cancelProductReserveCommand)
                .expectEvents(ProductReserveCancelledEvent.builder()
                        .productId(productId)
                        .userId("userId")
                        .orderId("orderId")
                        .quantity(2)
                        .build());
    }

}