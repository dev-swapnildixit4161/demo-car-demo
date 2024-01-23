package com.knoldus.cloudfunction.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class VehicleTest {

    /**
     * Method under test: {@link Vehicle#canEqual(Object)}
     */
    @Test
    public void testCanEqual() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        String string = "Other";

        // Act
        boolean actualCanEqualResult = vehicle.canEqual(string);

        // Assert
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link Vehicle#canEqual(Object)}
     */
    @Test
    public void testCanEqual2() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualCanEqualResult = vehicle.canEqual(vehicle2);

        // Assert
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);
        Vehicle vehicle2 = null;

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals2() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);
        String string = "Different type to Vehicle";

        // Act
        boolean actualEqualsResult = vehicle.equals(string);

        // Assert
        assertNotEquals(vehicle, string);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals3() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Model");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals4() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(null);
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals5() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(2);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals6() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(null);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals7() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Model");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals8() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor(null);
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals9() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(null);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals10() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(0.5d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals11() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Brand");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals12() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel(null);
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals13() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(null);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals14() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(0.5d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals15() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(3);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals16() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(null);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals17() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(null);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals18() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(0.5d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals19() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(3);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    public void testEquals20() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(null);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Vehicle#equals(Object)}
     *   <li>{@link Vehicle#hashCode()}
     * </ul>
     */
    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle);

        // Assert
        assertEquals(vehicle, vehicle);
        int expectedHashCodeResult = vehicle.hashCode();
        assertEquals(expectedHashCodeResult, vehicle.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Vehicle#equals(Object)}
     *   <li>{@link Vehicle#hashCode()}
     * </ul>
     */
    @Test
    public void testEqualsAndHashCode2() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertEquals(vehicle, vehicle2);
        int expectedHashCodeResult = vehicle.hashCode();
        assertEquals(expectedHashCodeResult, vehicle2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Vehicle#equals(Object)}
     *   <li>{@link Vehicle#hashCode()}
     * </ul>
     */
    @Test
    public void testEqualsAndHashCode3() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(null);
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand(null);
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertEquals(vehicle, vehicle2);
        int expectedHashCodeResult = vehicle.hashCode();
        assertEquals(expectedHashCodeResult, vehicle2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Vehicle#equals(Object)}
     *   <li>{@link Vehicle#hashCode()}
     * </ul>
     */
    @Test
    public void testEqualsAndHashCode4() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(null);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(null);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertEquals(vehicle, vehicle2);
        int expectedHashCodeResult = vehicle.hashCode();
        assertEquals(expectedHashCodeResult, vehicle2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Vehicle}
     *   <li>{@link Vehicle#setBrand(String)}
     *   <li>{@link Vehicle#setCarId(Integer)}
     *   <li>{@link Vehicle#setColor(String)}
     *   <li>{@link Vehicle#setMileage(Double)}
     *   <li>{@link Vehicle#setModel(String)}
     *   <li>{@link Vehicle#setPrice(Double)}
     *   <li>{@link Vehicle#setQuantity(Integer)}
     *   <li>{@link Vehicle#setTax(Double)}
     *   <li>{@link Vehicle#setYear(Integer)}
     *   <li>{@link Vehicle#toString()}
     *   <li>{@link Vehicle#getBrand()}
     *   <li>{@link Vehicle#getCarId()}
     *   <li>{@link Vehicle#getColor()}
     *   <li>{@link Vehicle#getMileage()}
     *   <li>{@link Vehicle#getModel()}
     *   <li>{@link Vehicle#getPrice()}
     *   <li>{@link Vehicle#getQuantity()}
     *   <li>{@link Vehicle#getTax()}
     *   <li>{@link Vehicle#getYear()}
     * </ul>
     */
    @Test
    public void testGettersAndSetters() {
        // Arrange and Act
        Vehicle actualVehicle = new Vehicle();
        String brand = "Brand";
        actualVehicle.setBrand(brand);
        int carId = 1;
        actualVehicle.setCarId(carId);
        String color = "Color";
        actualVehicle.setColor(color);
        double mileage = 10.0d;
        actualVehicle.setMileage(mileage);
        String model = "Model";
        actualVehicle.setModel(model);
        double price = 10.0d;
        actualVehicle.setPrice(price);
        int quantity = 1;
        actualVehicle.setQuantity(quantity);
        double tax = 10.0d;
        actualVehicle.setTax(tax);
        int year = 1;
        actualVehicle.setYear(year);
        String actualToStringResult = actualVehicle.toString();
        String actualBrand = actualVehicle.getBrand();
        Integer actualCarId = actualVehicle.getCarId();
        String actualColor = actualVehicle.getColor();
        Double actualMileage = actualVehicle.getMileage();
        String actualModel = actualVehicle.getModel();
        Double actualPrice = actualVehicle.getPrice();
        Integer actualQuantity = actualVehicle.getQuantity();
        Double actualTax = actualVehicle.getTax();
        Integer actualYear = actualVehicle.getYear();

        // Assert that nothing has changed
        assertEquals("Brand", actualBrand);
        assertEquals("Color", actualColor);
        assertEquals("Model", actualModel);
        assertEquals("Vehicle(carId=1, model=Model, brand=Brand, year=1, color=Color, mileage=10.0, price=10.0, quantity=1,"
                + " tax=10.0)", actualToStringResult);
        assertEquals(1, actualCarId.intValue());
        assertEquals(1, actualQuantity.intValue());
        assertEquals(1, actualYear.intValue());
        assertEquals(10.0d, actualMileage.doubleValue(), 0.0);
        assertEquals(10.0d, actualPrice.doubleValue(), 0.0);
        assertEquals(10.0d, actualTax.doubleValue(), 0.0);
    }

    // Creating a new instance of Vehicle with valid parameters should succeed.
    @Test
    public void test_create_instance_with_valid_parameters() {
        Vehicle vehicle = new Vehicle();
        vehicle.setCarId(1);
        vehicle.setModel("Model");
        vehicle.setBrand("Brand");
        vehicle.setYear(2022);
        vehicle.setColor("Red");
        vehicle.setMileage(100.0);
        vehicle.setPrice(20000.0);
        vehicle.setQuantity(10);
        vehicle.setTax(5.0);
        assertNotNull(vehicle);
        assertEquals(1, vehicle.getCarId().intValue());
        assertEquals("Model", vehicle.getModel());
        assertEquals("Brand", vehicle.getBrand());
        assertEquals(2022, vehicle.getYear().intValue());
        assertEquals("Red", vehicle.getColor());
        assertEquals(100.0, vehicle.getMileage(), 0.0);
        assertEquals(20000.0, vehicle.getPrice(), 0.0);
        assertEquals(10, vehicle.getQuantity().intValue());
        assertEquals(5.0, vehicle.getTax(), 0.0);
    }

    // All attributes of a Vehicle instance should be accessible and modifiable.
    @Test
    public void test_attributes_accessibility_and_modifiability() {
        Vehicle vehicle = new Vehicle();
        vehicle.setCarId(1);
        vehicle.setModel("Model");
        vehicle.setBrand("Brand");
        vehicle.setYear(2022);
        vehicle.setColor("Red");
        vehicle.setMileage(100.0);
        vehicle.setPrice(20000.0);
        vehicle.setQuantity(10);
        vehicle.setTax(5.0);

        assertEquals(1, vehicle.getCarId().intValue());
        assertEquals("Model", vehicle.getModel());
        assertEquals("Brand", vehicle.getBrand());
        assertEquals(2022, vehicle.getYear().intValue());
        assertEquals("Red", vehicle.getColor());
        assertEquals(100.0, vehicle.getMileage(), 0.0);
        assertEquals(20000.0, vehicle.getPrice(), 0.0);
        assertEquals(10, vehicle.getQuantity().intValue());
        assertEquals(5.0, vehicle.getTax(), 0.0);
    }

    // Two Vehicle instances with the same attributes should be considered equal.
    @Test
    public void test_instances_equality() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setCarId(1);
        vehicle1.setModel("Model");
        vehicle1.setBrand("Brand");
        vehicle1.setYear(2022);
        vehicle1.setColor("Red");
        vehicle1.setMileage(100.0);
        vehicle1.setPrice(20000.0);
        vehicle1.setQuantity(10);
        vehicle1.setTax(5.0);
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setCarId(1);
        vehicle2.setModel("Model");
        vehicle2.setBrand("Brand");
        vehicle2.setYear(2022);
        vehicle2.setColor("Red");
        vehicle2.setMileage(100.0);
        vehicle2.setPrice(20000.0);
        vehicle2.setQuantity(10);
        vehicle2.setTax(5.0);

        assertEquals(vehicle1, vehicle2);
    }

}