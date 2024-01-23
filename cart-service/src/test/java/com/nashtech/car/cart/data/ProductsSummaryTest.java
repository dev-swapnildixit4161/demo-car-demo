package com.nashtech.car.cart.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ProductsSummaryTest {
    /**
     * Method under test: {@link ProductsSummary#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ProductsSummary()).canEqual("Other"));
    }

    /**
     * Method under test: {@link ProductsSummary#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertTrue(productsSummary.canEqual(productsSummary2));
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        // Act and Assert
        assertNotEquals(null, productsSummary);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        // Act and Assert
        assertNotEquals("Different type to ProductsSummary", productsSummary);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(null);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(1.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("42");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand(null);
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("42");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor(null);
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(null);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(1.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("42");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel(null);
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("Brand");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals14() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId(null);
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals15() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(3);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals16() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(null);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals17() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(null);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals18() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(0.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals19() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(3);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Method under test: {@link ProductsSummary#equals(Object)}
     */
    @Test
    void testEquals20() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(null);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertNotEquals(productsSummary, productsSummary2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProductsSummary#equals(Object)}
     *   <li>{@link ProductsSummary#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        // Act and Assert
        assertEquals(productsSummary, productsSummary);
        int expectedHashCodeResult = productsSummary.hashCode();
        assertEquals(expectedHashCodeResult, productsSummary.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProductsSummary#equals(Object)}
     *   <li>{@link ProductsSummary#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertEquals(productsSummary, productsSummary2);
        int expectedHashCodeResult = productsSummary.hashCode();
        assertEquals(expectedHashCodeResult, productsSummary2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProductsSummary#equals(Object)}
     *   <li>{@link ProductsSummary#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(null);
        productsSummary.setBrand("Brand");
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(null);
        productsSummary2.setBrand("Brand");
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertEquals(productsSummary, productsSummary2);
        int expectedHashCodeResult = productsSummary.hashCode();
        assertEquals(expectedHashCodeResult, productsSummary2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProductsSummary#equals(Object)}
     *   <li>{@link ProductsSummary#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        ProductsSummary productsSummary = new ProductsSummary();
        productsSummary.setBasePrice(10.0d);
        productsSummary.setBrand(null);
        productsSummary.setColor("Color");
        productsSummary.setMileage(10.0d);
        productsSummary.setModel("Model");
        productsSummary.setProductId("42");
        productsSummary.setQuantity(1);
        productsSummary.setTax(10.0f);
        productsSummary.setYear(1);

        ProductsSummary productsSummary2 = new ProductsSummary();
        productsSummary2.setBasePrice(10.0d);
        productsSummary2.setBrand(null);
        productsSummary2.setColor("Color");
        productsSummary2.setMileage(10.0d);
        productsSummary2.setModel("Model");
        productsSummary2.setProductId("42");
        productsSummary2.setQuantity(1);
        productsSummary2.setTax(10.0f);
        productsSummary2.setYear(1);

        // Act and Assert
        assertEquals(productsSummary, productsSummary2);
        int expectedHashCodeResult = productsSummary.hashCode();
        assertEquals(expectedHashCodeResult, productsSummary2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link ProductsSummary}
     *   <li>{@link ProductsSummary#setBasePrice(Double)}
     *   <li>{@link ProductsSummary#setBrand(String)}
     *   <li>{@link ProductsSummary#setColor(String)}
     *   <li>{@link ProductsSummary#setMileage(Double)}
     *   <li>{@link ProductsSummary#setModel(String)}
     *   <li>{@link ProductsSummary#setProductId(String)}
     *   <li>{@link ProductsSummary#setQuantity(Integer)}
     *   <li>{@link ProductsSummary#setTax(Float)}
     *   <li>{@link ProductsSummary#setYear(Integer)}
     *   <li>{@link ProductsSummary#toString()}
     *   <li>{@link ProductsSummary#getBasePrice()}
     *   <li>{@link ProductsSummary#getBrand()}
     *   <li>{@link ProductsSummary#getColor()}
     *   <li>{@link ProductsSummary#getMileage()}
     *   <li>{@link ProductsSummary#getModel()}
     *   <li>{@link ProductsSummary#getProductId()}
     *   <li>{@link ProductsSummary#getQuantity()}
     *   <li>{@link ProductsSummary#getTax()}
     *   <li>{@link ProductsSummary#getYear()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ProductsSummary actualProductsSummary = new ProductsSummary();
        actualProductsSummary.setBasePrice(10.0d);
        actualProductsSummary.setBrand("Brand");
        actualProductsSummary.setColor("Color");
        actualProductsSummary.setMileage(10.0d);
        actualProductsSummary.setModel("Model");
        actualProductsSummary.setProductId("42");
        actualProductsSummary.setQuantity(1);
        actualProductsSummary.setTax(10.0f);
        actualProductsSummary.setYear(1);
        String actualToStringResult = actualProductsSummary.toString();
        Double actualBasePrice = actualProductsSummary.getBasePrice();
        String actualBrand = actualProductsSummary.getBrand();
        String actualColor = actualProductsSummary.getColor();
        Double actualMileage = actualProductsSummary.getMileage();
        String actualModel = actualProductsSummary.getModel();
        String actualProductId = actualProductsSummary.getProductId();
        Integer actualQuantity = actualProductsSummary.getQuantity();
        Float actualTax = actualProductsSummary.getTax();
        Integer actualYear = actualProductsSummary.getYear();

        // Assert that nothing has changed
        assertEquals("42", actualProductId);
        assertEquals("Brand", actualBrand);
        assertEquals("Color", actualColor);
        assertEquals("Model", actualModel);
        assertEquals(
                "ProductsSummary(productId=42, brand=Brand, model=Model, year=1, color=Color, mileage=10.0, basePrice=10.0,"
                        + " quantity=1, tax=10.0)",
                actualToStringResult);
        assertEquals(1, actualQuantity.intValue());
        assertEquals(1, actualYear.intValue());
        assertEquals(10.0d, actualBasePrice.doubleValue());
        assertEquals(10.0d, actualMileage.doubleValue());
        assertEquals(10.0f, actualTax.floatValue());
    }
}
