package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apis/car")
public class CarEntityController {

    private final CarService service;

    @Autowired
    public CarEntityController(CarService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarEntity>> getAllCarEntity() {
        return new ResponseEntity<>(service.getAllCarEntity(), HttpStatus.OK);
    }

    @GetMapping("/byId/{carId}")
    public ResponseEntity<CarEntity> getCarDetailsById(@PathVariable("carId") String carId) {
        CarEntity carEntityWithCarId = service.getCarEntityWithCarId(Integer.valueOf(carId));
        return new ResponseEntity<>(carEntityWithCarId, HttpStatus.OK);
    }

    @GetMapping("/byMileage/{mileage}")
    public ResponseEntity<List<CarEntity>> getCarDetailsByMileage(@PathVariable("mileage") String mileage) {
        List<CarEntity> carEntityWithCarMileage = service.getCarEntityWithCarMileage(Double.valueOf(mileage));
        return new ResponseEntity<>(carEntityWithCarMileage, HttpStatus.OK);
    }

    @GetMapping("/byBrand/{brand}")
    public ResponseEntity<List<CarEntity>> getCarDetailsByBrand(@PathVariable("brand") String brand) {
        List<CarEntity> carEntityWithCarBrand = service.getCarEntityWithBrandName(brand);
        return new ResponseEntity<>(carEntityWithCarBrand, HttpStatus.OK);
    }

    @GetMapping("/byPrice/{price}")
    public ResponseEntity<List<CarEntity>> getCarDetailsByPrice(@PathVariable("price") String price) {
        List<CarEntity> carEntityWithCarPrice = service.getCarEntityWithCarPrice(Double.valueOf(price));
        return new ResponseEntity<>(carEntityWithCarPrice, HttpStatus.OK);
    }
}
