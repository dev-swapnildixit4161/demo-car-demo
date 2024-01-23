package com.elasticsearch.elasticsearch.util;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CarEntitySerializerTest {
    private CarEntitySerializer carEntitySerializer;

    @BeforeEach
    void setUp() {
        carEntitySerializer = new CarEntitySerializer();
        carEntitySerializer.configure(Map.of(), false);
    }

    @Test
    void serializeShouldNotThrowException() {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarId(123);
        carEntity.setModel("Toyota");
        // set other fields as needed

        assertDoesNotThrow(() -> {
            byte[] serializedData = carEntitySerializer.serialize("topic", carEntity);
            assertNotNull(serializedData);

            // Deserialize the data directly for verification
            CarEntity deserializedCarEntity = deserialize(serializedData);
            assertEquals(carEntity.getCarId(), deserializedCarEntity.getCarId());
            assertEquals(carEntity.getModel(), deserializedCarEntity.getModel());
            // verify other fields
        });
    }

    private CarEntity deserialize(byte[] data) {
        try {
            // Implement your deserialization logic here
            // For example, using Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(data, CarEntity.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing CarEntity", e);
        }
    }
}
