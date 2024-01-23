package com.knoldus.function.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarUtilTest {
    /**
     * Method under test: {@link CarUtil#updateMileage(Double)}
     */
    @Test
    public void testUpdateMileage() {
        // Arrange
        double mileage = 10.0d;

        // Act
        Double actualUpdateMileageResult = CarUtil.updateMileage(mileage);

        // Assert
        assertEquals(16.093440006147d, actualUpdateMileageResult.doubleValue());
    }

    /**
     * Method under test: {@link CarUtil#updatePrice(Double)}
     */
    @Test
    public void testUpdatePrice() {
        // Arrange
        double price = 10.0d;

        // Act
        Double actualUpdatePriceResult = CarUtil.updatePrice(price);

        // Assert
        assertEquals(821.0d, actualUpdatePriceResult.doubleValue());
    }
}
