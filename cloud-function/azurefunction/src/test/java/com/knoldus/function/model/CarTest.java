package com.knoldus.function.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarTest {
  /**
   * Method under test: {@link Car#canEqual(Object)}
   */
  @Test
  public void testCanEqual() {
    // Arrange
    Car car = new Car();
    String string = "Other";

    // Act
    boolean actualCanEqualResult = car.canEqual(string);

    // Assert
    assertFalse(actualCanEqualResult);
  }

  /**
   * Method under test: {@link Car#canEqual(Object)}
   */
  @Test
  public void testCanEqual2() {
    // Arrange
    Car car = new Car();
    Car car2 = new Car();

    // Act
    boolean actualCanEqualResult = car.canEqual(car2);

    // Assert
    assertTrue(actualCanEqualResult);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals() {
    // Arrange
    Car car = new Car();
    Car car2 = null;

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals2() {
    // Arrange
    Car car = new Car();
    String string = "Different type to Car";

    // Act

    // Assert
    assertNotEquals(car, string);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals3() {
    // Arrange
    Car car = new Car(1, "Brand", "Model", 1L, "Color", 10.0d, 10.0d, 1, 10.0d);
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals4() {
    // Arrange
    Car car = new Car();
    Car car2 = new Car(1, "Brand", "Model", 1L, "Color", 10.0d, 10.0d, 1, 10.0d);

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals5() {
    // Arrange
    Car car = new Car();
    car.setBrand("Brand");
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals6() {
    // Arrange
    Car car = new Car();
    car.setModel("Model");
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals7() {
    // Arrange
    Car car = new Car();
    car.setYear(1L);
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals8() {
    // Arrange
    Car car = new Car();
    car.setColor("Color");
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals9() {
    // Arrange
    Car car = new Car();
    car.setMileage(10.0d);
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals10() {
    // Arrange
    Car car = new Car();
    car.setPrice(10.0d);
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals11() {
    // Arrange
    Car car = new Car();
    car.setQuantity(1);
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals12() {
    // Arrange
    Car car = new Car();
    car.setTax(10.0d);
    Car car2 = new Car();

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals13() {
    // Arrange
    Car car = new Car();

    Car car2 = new Car();
    car2.setBrand("Brand");

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals14() {
    // Arrange
    Car car = new Car();

    Car car2 = new Car();
    car2.setModel("Model");

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals15() {
    // Arrange
    Car car = new Car();

    Car car2 = new Car();
    car2.setYear(1L);

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals16() {
    // Arrange
    Car car = new Car();

    Car car2 = new Car();
    car2.setColor("Color");

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals17() {
    // Arrange
    Car car = new Car();

    Car car2 = new Car();
    car2.setMileage(10.0d);

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals18() {
    // Arrange
    Car car = new Car();

    Car car2 = new Car();
    car2.setPrice(10.0d);

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals19() {
    // Arrange
    Car car = new Car();

    Car car2 = new Car();
    car2.setQuantity(1);

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Method under test: {@link Car#equals(Object)}
   */
  @Test
  public void testEquals20() {
    // Arrange
    Car car = new Car();

    Car car2 = new Car();
    car2.setTax(10.0d);

    // Act

    // Assert
    assertNotEquals(car, car2);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Car#equals(Object)}
   *   <li>{@link Car#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode() {
    // Arrange
    Car car = new Car();

    // Act

    // Assert
    assertEquals(car, car);
    int expectedHashCodeResult = car.hashCode();
    assertEquals(expectedHashCodeResult, car.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Car#equals(Object)}
   *   <li>{@link Car#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode2() {
    // Arrange
    Car car = new Car();
    Car car2 = new Car();

    // Act

    // Assert
    assertEquals(car, car2);
    int expectedHashCodeResult = car.hashCode();
    assertEquals(expectedHashCodeResult, car2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Car#equals(Object)}
   *   <li>{@link Car#hashCode()}
   * </ul>
   */
  @Test
  public void testEqualsAndHashCode3() {
    // Arrange
    Car car = new Car(1, "Brand", "Model", 1L, "Color", 10.0d, 10.0d, 1, 10.0d);
    Car car2 = new Car(1, "Brand", "Model", 1L, "Color", 10.0d, 10.0d, 1, 10.0d);

    // Act

    // Assert
    assertEquals(car, car2);
    int expectedHashCodeResult = car.hashCode();
    assertEquals(expectedHashCodeResult, car2.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Car#Car()}
   *   <li>{@link Car#setBrand(String)}
   *   <li>{@link Car#setCarId(Integer)}
   *   <li>{@link Car#setColor(String)}
   *   <li>{@link Car#setMileage(Double)}
   *   <li>{@link Car#setModel(String)}
   *   <li>{@link Car#setPrice(Double)}
   *   <li>{@link Car#setQuantity(Integer)}
   *   <li>{@link Car#setTax(Double)}
   *   <li>{@link Car#setYear(Long)}
   *   <li>{@link Car#toString()}
   *   <li>{@link Car#getBrand()}
   *   <li>{@link Car#getCarId()}
   *   <li>{@link Car#getColor()}
   *   <li>{@link Car#getMileage()}
   *   <li>{@link Car#getModel()}
   *   <li>{@link Car#getPrice()}
   *   <li>{@link Car#getQuantity()}
   *   <li>{@link Car#getTax()}
   *   <li>{@link Car#getYear()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters() {
    // Arrange and Act
    Car actualCar = new Car();
    String brand = "Brand";
    actualCar.setBrand(brand);
    int carId = 1;
    actualCar.setCarId(carId);
    String color = "Color";
    actualCar.setColor(color);
    double mileage = 10.0d;
    actualCar.setMileage(mileage);
    String model = "Model";
    actualCar.setModel(model);
    double price = 10.0d;
    actualCar.setPrice(price);
    int quantity = 1;
    actualCar.setQuantity(quantity);
    double tax = 10.0d;
    actualCar.setTax(tax);
    long year = 1L;
    actualCar.setYear(year);
    String actualToStringResult = actualCar.toString();
    String actualBrand = actualCar.getBrand();
    Integer actualCarId = actualCar.getCarId();
    String actualColor = actualCar.getColor();
    Double actualMileage = actualCar.getMileage();
    String actualModel = actualCar.getModel();
    Double actualPrice = actualCar.getPrice();
    Integer actualQuantity = actualCar.getQuantity();
    Double actualTax = actualCar.getTax();
    Long actualYear = actualCar.getYear();

    // Assert that nothing has changed
    assertEquals("Brand", actualBrand);
    assertEquals("Car(carId=1, brand=Brand, model=Model, year=1, color=Color, mileage=10.0, price=10.0, quantity=1,"
            + " tax=10.0)", actualToStringResult);
    assertEquals("Color", actualColor);
    assertEquals("Model", actualModel);
    assertEquals(1, actualCarId.intValue());
    assertEquals(1, actualQuantity.intValue());
    assertEquals(10.0d, actualMileage.doubleValue());
    assertEquals(10.0d, actualPrice.doubleValue());
    assertEquals(10.0d, actualTax.doubleValue());
    assertEquals(1L, actualYear.longValue());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>
   * {@link Car#Car(Integer, String, String, Long, String, Double, Double, Integer, Double)}
   *   <li>{@link Car#setBrand(String)}
   *   <li>{@link Car#setCarId(Integer)}
   *   <li>{@link Car#setColor(String)}
   *   <li>{@link Car#setMileage(Double)}
   *   <li>{@link Car#setModel(String)}
   *   <li>{@link Car#setPrice(Double)}
   *   <li>{@link Car#setQuantity(Integer)}
   *   <li>{@link Car#setTax(Double)}
   *   <li>{@link Car#setYear(Long)}
   *   <li>{@link Car#toString()}
   *   <li>{@link Car#getBrand()}
   *   <li>{@link Car#getCarId()}
   *   <li>{@link Car#getColor()}
   *   <li>{@link Car#getMileage()}
   *   <li>{@link Car#getModel()}
   *   <li>{@link Car#getPrice()}
   *   <li>{@link Car#getQuantity()}
   *   <li>{@link Car#getTax()}
   *   <li>{@link Car#getYear()}
   * </ul>
   */
  @Test
  public void testGettersAndSetters2() {
    // Arrange
    int carId = 1;
    String brand = "Brand";
    String model = "Model";
    long year = 1L;
    String color = "Color";
    double mileage = 10.0d;
    double price = 10.0d;
    int quantity = 1;
    double tax = 10.0d;

    // Act
    Car actualCar = new Car(carId, brand, model, year, color, mileage, price, quantity, tax);
    String brand2 = "Brand";
    actualCar.setBrand(brand2);
    int carId2 = 1;
    actualCar.setCarId(carId2);
    String color2 = "Color";
    actualCar.setColor(color2);
    double mileage2 = 10.0d;
    actualCar.setMileage(mileage2);
    String model2 = "Model";
    actualCar.setModel(model2);
    double price2 = 10.0d;
    actualCar.setPrice(price2);
    int quantity2 = 1;
    actualCar.setQuantity(quantity2);
    double tax2 = 10.0d;
    actualCar.setTax(tax2);
    long year2 = 1L;
    actualCar.setYear(year2);
    String actualToStringResult = actualCar.toString();
    String actualBrand = actualCar.getBrand();
    Integer actualCarId = actualCar.getCarId();
    String actualColor = actualCar.getColor();
    Double actualMileage = actualCar.getMileage();
    String actualModel = actualCar.getModel();
    Double actualPrice = actualCar.getPrice();
    Integer actualQuantity = actualCar.getQuantity();
    Double actualTax = actualCar.getTax();
    Long actualYear = actualCar.getYear();

    // Assert that nothing has changed
    assertEquals("Brand", actualBrand);
    assertEquals("Car(carId=1, brand=Brand, model=Model, year=1, color=Color, mileage=10.0, price=10.0, quantity=1,"
            + " tax=10.0)", actualToStringResult);
    assertEquals("Color", actualColor);
    assertEquals("Model", actualModel);
    assertEquals(1, actualCarId.intValue());
    assertEquals(1, actualQuantity.intValue());
    assertEquals(10.0d, actualMileage.doubleValue());
    assertEquals(10.0d, actualPrice.doubleValue());
    assertEquals(10.0d, actualTax.doubleValue());
    assertEquals(1L, actualYear.longValue());
  }
}
