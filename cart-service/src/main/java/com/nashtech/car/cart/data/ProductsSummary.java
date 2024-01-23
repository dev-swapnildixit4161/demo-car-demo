package com.nashtech.car.cart.data;

import lombok.Data;

@Data
public class ProductsSummary {
    private String productId;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private Double mileage = 0.0d;
    private Double basePrice = 0.0d;
    private Integer quantity =0;
    private Float tax = 0.0f;

}