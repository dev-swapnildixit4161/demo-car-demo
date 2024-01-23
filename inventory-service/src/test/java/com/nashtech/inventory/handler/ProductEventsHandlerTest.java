package com.nashtech.inventory.handler;

import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.events.ProductCreatedEvent;
import com.nashtech.inventory.repository.ProductEntity;
import com.nashtech.inventory.repository.ProductsRepository;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;

/**
 * This class contains JUnit tests for the {@link ProductEventsHandler} class.
 * It utilizes Mockito for mocking dependencies and focuses on testing event handling methods.
 */
@ContextConfiguration(classes = {ProductEventsHandler.class})
@ExtendWith(SpringExtension.class)
class ProductEventsHandlerTest {
    @Autowired
    private ProductEventsHandler productEventsHandler;
    @MockBean
    private ProductsRepository productsRepository;

    /**
     * Test case for handling the {@link ProductReserveCancelledEvent}.
     * Verifies that the repository methods {@code findByProductId} and {@code save} are called appropriately.
     */
    @Test
    void testOn() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBasePrice(10.0d);
        productEntity.setBrand("Brand");
        productEntity.setColor("Color");
        productEntity.setMileage(10.0d);
        productEntity.setModel("Model");
        productEntity.setProductId("42");
        productEntity.setQuantity(1);
        productEntity.setTax(10.0f);
        productEntity.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity.setYear(1);

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setBasePrice(10.0d);
        productEntity2.setBrand("Brand");
        productEntity2.setColor("Color");
        productEntity2.setMileage(10.0d);
        productEntity2.setModel("Model");
        productEntity2.setProductId("42");
        productEntity2.setQuantity(1);
        productEntity2.setTax(10.0f);
        productEntity2.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity2.setYear(1);
        when(productsRepository.save(Mockito.any())).thenReturn(productEntity2);
        when(productsRepository.findByProductId(Mockito.any())).thenReturn(productEntity);
        ProductReserveCancelledEvent productReservationCancelledEvent = ProductReserveCancelledEvent.builder()
                .orderId("42")
                .productId("42")
                .quantity(1)
                .userId("42")
                .build();

        productEventsHandler.on(productReservationCancelledEvent);

        verify(productsRepository).findByProductId(Mockito.any());
        verify(productsRepository).save(Mockito.any());
    }

    /**
     * Test case for handling the {@link ProductReservedEvent}.
     * Verifies that the repository methods {@code findByProductId} and {@code save} are called appropriately.
     */
    @Test
    void testOn2() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBasePrice(10.0d);
        productEntity.setBrand("Brand");
        productEntity.setColor("Color");
        productEntity.setMileage(10.0d);
        productEntity.setModel("Model");
        productEntity.setProductId("42");
        productEntity.setQuantity(1);
        productEntity.setTax(10.0f);
        productEntity.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity.setYear(1);

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setBasePrice(10.0d);
        productEntity2.setBrand("Brand");
        productEntity2.setColor("Color");
        productEntity2.setMileage(10.0d);
        productEntity2.setModel("Model");
        productEntity2.setProductId("42");
        productEntity2.setQuantity(1);
        productEntity2.setTax(10.0f);
        productEntity2.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity2.setYear(1);
        when(productsRepository.save(Mockito.any())).thenReturn(productEntity2);
        when(productsRepository.findByProductId(Mockito.any())).thenReturn(productEntity);
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

        productEventsHandler.on(productReservedEvent);

        verify(productsRepository).findByProductId(Mockito.any());
        verify(productsRepository).save(Mockito.any());
    }

    /**
     * Test case for handling the {@link ProductCreatedEvent}.
     * Verifies that the repository method {@code save} is called appropriately.
     */
    @Test
    void testOn3() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBasePrice(10.0d);
        productEntity.setBrand("Brand");
        productEntity.setColor("Color");
        productEntity.setMileage(10.0d);
        productEntity.setModel("Model");
        productEntity.setProductId("42");
        productEntity.setQuantity(1);
        productEntity.setTax(10.0f);
        productEntity.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity.setYear(1);
        when(productsRepository.save(Mockito.any())).thenReturn(productEntity);
        ProductCreatedEvent event = ProductCreatedEvent.builder()
                .basePrice(10.0d)
                .brand("Brand")
                .color("Color")
                .mileage(10.0d)
                .model("Model")
                .productId("42")
                .quantity(1)
                .tax(10.0f)
                .year(1)
                .build();

        productEventsHandler.on(event);

        verify(productsRepository).save(Mockito.any());
    }

    /**
     * Another test case for handling the {@link ProductReserveCancelledEvent}.
     * Verifies that the repository methods {@code findByProductId} and {@code save} are called appropriately.
     */
    @Test
    void testOn4() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBasePrice(10.0d);
        productEntity.setBrand("Brand");
        productEntity.setColor("Color");
        productEntity.setMileage(10.0d);
        productEntity.setModel("Model");
        productEntity.setProductId("42");
        productEntity.setQuantity(1);
        productEntity.setTax(10.0f);
        productEntity.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity.setYear(1);

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setBasePrice(10.0d);
        productEntity2.setBrand("Brand");
        productEntity2.setColor("Color");
        productEntity2.setMileage(10.0d);
        productEntity2.setModel("Model");
        productEntity2.setProductId("42");
        productEntity2.setQuantity(1);
        productEntity2.setTax(10.0f);
        productEntity2.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity2.setYear(1);
        when(productsRepository.save(Mockito.any())).thenReturn(productEntity2);
        when(productsRepository.findByProductId(Mockito.any())).thenReturn(productEntity);
        ProductReserveCancelledEvent productReservationCancelledEvent = ProductReserveCancelledEvent.builder()
                .orderId("42")
                .productId("42")
                .quantity(1)
                .userId("42")
                .build();
        productEventsHandler.on(productReservationCancelledEvent);

        verify(productsRepository).findByProductId(Mockito.any());
        verify(productsRepository).save(Mockito.any());
    }

    /**
     * Test case specifically for the handling of {@link ProductReservedEvent}.
     * Verifies that the repository methods {@code findByProductId} and {@code save} are called appropriately.
     */
    @Test
    void testProductReservedEventHandling() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBasePrice(10.0d);
        productEntity.setBrand("Brand");
        productEntity.setColor("Color");
        productEntity.setMileage(10.0d);
        productEntity.setModel("Model");
        productEntity.setProductId("42");
        productEntity.setQuantity(1);
        productEntity.setTax(10.0f);
        productEntity.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity.setYear(1);

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setBasePrice(10.0d);
        productEntity2.setBrand("Brand");
        productEntity2.setColor("Color");
        productEntity2.setMileage(10.0d);
        productEntity2.setModel("Model");
        productEntity2.setProductId("42");
        productEntity2.setQuantity(1);
        productEntity2.setTax(10.0f);
        productEntity2.setTimestamp(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        productEntity2.setYear(1);
        when(productsRepository.save(Mockito.any())).thenReturn(productEntity2);
        when(productsRepository.findByProductId(Mockito.any())).thenReturn(productEntity);
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
        productEventsHandler.on(productReservedEvent);
        verify(productsRepository).findByProductId(Mockito.any());
        verify(productsRepository).save(Mockito.any());
    }
}
