package com.nashtech.inventory.query;

import lombok.Data;

@Data
public class ProductsSummary {
    private String productId;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private Double mileage;
    private Double basePrice;
    private Integer quantity;
    private Float tax;

}
