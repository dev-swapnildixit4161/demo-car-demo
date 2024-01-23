package com.nashtech.inventory.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class ProductEntityTest {
    /**
     * Method under test: {@link ProductEntity#equals(Object)}
     */
    @Test
    void testEquals() {
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

        assertNotEquals(null,productEntity);
    }

    /**
     * Method under test: {@link ProductEntity#equals(Object)}
     */
    @Test
    void testEquals3() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBasePrice(10.0d);
        productEntity.setBrand("Brand");
        productEntity.setColor("Color");
        productEntity.setMileage(10.0d);
        productEntity.setModel("Model");
        productEntity.setProductId("Product Id");
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

        assertNotEquals(productEntity, productEntity2);
    }

    /**
     * Method under test: {@link ProductEntity#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBasePrice(10.0d);
        productEntity.setBrand("Brand");
        productEntity.setColor("Color");
        productEntity.setMileage(10.0d);
        productEntity.setModel("Model");
        productEntity.setProductId(null);
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

        assertNotEquals(productEntity, productEntity2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProductEntity#equals(Object)}
     *   <li>{@link ProductEntity#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
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

        assertEquals(productEntity, productEntity);
        int expectedHashCodeResult = productEntity.hashCode();
        assertEquals(expectedHashCodeResult, productEntity.hashCode());
    }

    /**
     * Method under test: {@link ProductEntity#equals(Object)}
     */
    @Test
    void testEquals2() {
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

        assertNotEquals( "Different type to ProductEntity", productEntity);
    }
}
