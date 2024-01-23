package com.elasticsearch.elasticsearch.util;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class CarMapper {

    private CarMapper(){
    }

    public static CarEntity mapStringToEntity(String payload) {

        ObjectMapper objectMapper = new ObjectMapper();
        CarEntity carEntity = new CarEntity();
        try {
            Map<String, Object> payloadMap = objectMapper.readValue(payload, new TypeReference<Map<String, Object>>() {
            });
            log.info("After conversion : {}", payloadMap);
            carEntity.setId((String) payloadMap.get("id"));
            carEntity.setCarId((Integer) payloadMap.get("carId"));
            carEntity.setBrand((String) payloadMap.get("brand"));
            carEntity.setModel((String) payloadMap.get("model"));
            carEntity.setYear(Long.valueOf((Integer) payloadMap.get("year")));
            carEntity.setColor((String) payloadMap.get("color"));
            carEntity.setMileage((Double) payloadMap.get("mileage"));
            carEntity.setPrice((Double) payloadMap.get("price"));
            carEntity.setQuantity((Integer) payloadMap.get("quantity"));
            carEntity.setTax((Double) payloadMap.get("tax"));
        } catch (JsonProcessingException e) {
            log.error("payload is not valid {} ", e.getMessage());

        }

        return carEntity;
    }
}
