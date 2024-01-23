package com.elasticsearch.elasticsearch.repository;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarEntityRepository extends ElasticsearchRepository<CarEntity, String> {


    CarEntity findByCarId(Integer carId);

    List<CarEntity> findByBrand(String brand);

    List<CarEntity> findByMileageGreaterThanEqual(Double mileage);

    List<CarEntity> findByPriceGreaterThanEqual(Double price);
}
