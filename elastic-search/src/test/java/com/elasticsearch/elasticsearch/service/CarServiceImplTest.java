package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.repository.CarEntityRepository;
import com.elasticsearch.elasticsearch.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarEntityRepository repository;

    @Test
    void testGetCarEntityWithCarId() {
        CarEntity mockCarEntity = new CarEntity();
        when(repository.findByCarId(any(Integer.class))).thenReturn(mockCarEntity);

        CarEntity result = carService.getCarEntityWithCarId(1);

        assertEquals(mockCarEntity, result);
    }

    @Test
    void testGetCarEntityWithBrandName() {
        List<CarEntity> mockCarEntities = Arrays.asList(new CarEntity(), new CarEntity());
        when(repository.findByBrand(any(String.class))).thenReturn(mockCarEntities);

        List<CarEntity> result = carService.getCarEntityWithBrandName("Toyota");

        assertEquals(mockCarEntities, result);
    }

    @Test
    void testGetCarEntityWithCarPrice() {
        List<CarEntity> mockCarEntities = Arrays.asList(new CarEntity(), new CarEntity());
        when(repository.findByPriceGreaterThanEqual(any(Double.class))).thenReturn(mockCarEntities);

        List<CarEntity> result = carService.getCarEntityWithCarPrice(30000.0);

        assertEquals(mockCarEntities, result);
    }

    @Test
    void testGetCarEntityWithCarMileage() {
        List<CarEntity> mockCarEntities = Arrays.asList(new CarEntity(), new CarEntity());
        when(repository.findByMileageGreaterThanEqual(any(Double.class))).thenReturn(mockCarEntities);

        List<CarEntity> result = carService.getCarEntityWithCarMileage(50.5);

        assertEquals(mockCarEntities, result);
    }

    @Test
    void testGetAllCarEntity() {
        List<CarEntity> mockCarEntities = Arrays.asList(new CarEntity(), new CarEntity());
        when(repository.findAll()).thenReturn(mockCarEntities);

        List<CarEntity> result = carService.getAllCarEntity();

        assertEquals(mockCarEntities, result);
    }

    @Test
    void testSaveCarEntity() {
        CarEntity mockCarEntity = new CarEntity();
        when(repository.save(any(CarEntity.class))).thenReturn(mockCarEntity);

        CarEntity result = carService.saveCarEntity(new CarEntity());

        assertEquals(mockCarEntity, result);
    }
}