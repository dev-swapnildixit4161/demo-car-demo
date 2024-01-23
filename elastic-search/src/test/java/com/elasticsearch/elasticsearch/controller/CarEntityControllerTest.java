package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarEntityControllerTest {

    @InjectMocks
    private CarEntityController controller;

    @Mock
    private CarService service;

    @Test
    void testGetAllCarEntity() {
        List<CarEntity> mockCarEntities = Arrays.asList(new CarEntity(), new CarEntity());
        when(service.getAllCarEntity()).thenReturn(mockCarEntities);
        ResponseEntity<List<CarEntity>> responseEntity = controller.getAllCarEntity();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCarEntities, responseEntity.getBody());
    }

    @Test
    void testGetCarDetailsById() {
        CarEntity mockCarEntity = new CarEntity();
        when(service.getCarEntityWithCarId(any(Integer.class))).thenReturn(mockCarEntity);
        ResponseEntity<CarEntity> responseEntity = controller.getCarDetailsById("1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCarEntity, responseEntity.getBody());
    }

    @Test
    void testGetCarDetailsByMileage() {
        List<CarEntity> mockCarEntities = Arrays.asList(new CarEntity(), new CarEntity());
        when(service.getCarEntityWithCarMileage(any(Double.class))).thenReturn(mockCarEntities);

        ResponseEntity<List<CarEntity>> responseEntity = controller.getCarDetailsByMileage("50.5");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCarEntities, responseEntity.getBody());
    }

    @Test
    void testGetCarDetailsByBrand() {
        List<CarEntity> mockCarEntities = Arrays.asList(new CarEntity(), new CarEntity());
        when(service.getCarEntityWithBrandName(any(String.class))).thenReturn(mockCarEntities);

        ResponseEntity<List<CarEntity>> responseEntity = controller.getCarDetailsByBrand("Toyota");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCarEntities, responseEntity.getBody());
    }

    @Test
    void testGetCarDetailsByPrice() {
        List<CarEntity> mockCarEntities = Arrays.asList(new CarEntity(), new CarEntity());
        when(service.getCarEntityWithCarPrice(any(Double.class))).thenReturn(mockCarEntities);

        ResponseEntity<List<CarEntity>> responseEntity = controller.getCarDetailsByPrice("30000.0");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCarEntities, responseEntity.getBody());
    }

}