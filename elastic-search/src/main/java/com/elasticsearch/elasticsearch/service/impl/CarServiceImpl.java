package com.elasticsearch.elasticsearch.service.impl;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.repository.CarEntityRepository;
import com.elasticsearch.elasticsearch.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CarServiceImpl implements CarService {

    private final CarEntityRepository repository;

    public CarServiceImpl(CarEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public CarEntity getCarEntityWithCarId(Integer carId) {
        return repository.findByCarId(carId);
    }

    @Override
    public List<CarEntity> getCarEntityWithBrandName(String brand) {
        return repository.findByBrand(brand);
    }

    @Override
    public List<CarEntity> getCarEntityWithCarPrice(Double price) {
        return repository.findByPriceGreaterThanEqual(price);
    }

    @Override
    public List<CarEntity> getCarEntityWithCarMileage(Double mileage) {
        return repository.findByMileageGreaterThanEqual(mileage);
    }

    @Override
    public List<CarEntity> getAllCarEntity() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @Override
    public CarEntity saveCarEntity(CarEntity entity) {
        return repository.save(entity);
    }
}

