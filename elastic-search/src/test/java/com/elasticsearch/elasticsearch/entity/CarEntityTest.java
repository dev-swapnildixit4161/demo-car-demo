package com.elasticsearch.elasticsearch.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarEntityTest {

    @Test
    void testGetterAndSetter() {
        // Create a CarEntity object
        CarEntity carEntity = new CarEntity();

        // Set values using setters
        carEntity.setCarId(1);
        carEntity.setModel("TestModel");
        carEntity.setBrand("TestBrand");
        carEntity.setYear(2022L);
        carEntity.setColor("TestColor");
        carEntity.setMileage(50.5);
        carEntity.setPrice(30000.0);
        carEntity.setQuantity(5);
        carEntity.setTax(5.0);

        // Check values using getters
        assertEquals(1, carEntity.getCarId());
        assertEquals("TestModel", carEntity.getModel());
        assertEquals("TestBrand", carEntity.getBrand());
        assertEquals(2022L, carEntity.getYear());
        assertEquals("TestColor", carEntity.getColor());
        assertEquals(50.5, carEntity.getMileage());
        assertEquals(30000.0, carEntity.getPrice());
        assertEquals(5, carEntity.getQuantity());
        assertEquals(5.0, carEntity.getTax());
    }

    @Test
    void testEqualsAndHashCode() {
        // Create two CarEntity objects with the same values
        CarEntity carEntity1 = new CarEntity();
        carEntity1.setCarId(1);
        carEntity1.setModel("TestModel");
        carEntity1.setBrand("TestBrand");

        CarEntity carEntity2 = new CarEntity();
        carEntity2.setCarId(1);
        carEntity2.setModel("TestModel");
        carEntity2.setBrand("TestBrand");

        // Check if equals method works
        assertTrue(carEntity1.equals(carEntity2) && carEntity2.equals(carEntity1));

        // Check if hash codes are the same for equal objects
        assertEquals(carEntity1.hashCode(), carEntity2.hashCode());
    }

}