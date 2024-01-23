package com.elasticsearch.elasticsearch.service;

import com.elasticsearch.elasticsearch.entity.CarEntity;

import java.util.List;

public interface CarService {

    public CarEntity getCarEntityWithCarId(Integer carId);

    public List<CarEntity> getCarEntityWithBrandName(String brand);

    public List<CarEntity> getCarEntityWithCarPrice(Double price);

    public List<CarEntity> getCarEntityWithCarMileage(Double mileage);

    public List<CarEntity> getAllCarEntity();

    public CarEntity saveCarEntity(CarEntity entity);

}
