package com.elasticsearch.elasticsearch.util;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    @Test
    void mapStringToEntityShouldMapCorrectly() {
        // Given
        String payload = "{\"id\":\"123\", \"carId\": 456, \"brand\":\"Toyota\", \"model\":\"Camry\", \"year\": 2022, \"color\":\"Blue\", \"mileage\": 50000.5, \"price\": 25000.0, \"quantity\": 2, \"tax\": 10.0}";

        // When
        CarEntity carEntity = CarMapper.mapStringToEntity(payload);

        // Then
        assertNotNull(carEntity);
        assertEquals("123", carEntity.getId());
        assertEquals(456, carEntity.getCarId());
        assertEquals("Toyota", carEntity.getBrand());
        assertEquals("Camry", carEntity.getModel());
        assertEquals(2022, carEntity.getYear());
        assertEquals("Blue", carEntity.getColor());
        assertEquals(50000.5, carEntity.getMileage());
        assertEquals(25000.0, carEntity.getPrice());
        assertEquals(2, carEntity.getQuantity());
        assertEquals(10.0, carEntity.getTax());
    }

}