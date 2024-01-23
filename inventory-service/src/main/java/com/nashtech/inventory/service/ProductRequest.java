package com.nashtech.inventory.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {
	@JsonAlias("carId")
	private String productId;
	private String brand;
	private String model;
	private Integer year;
	private String color;
	private Double mileage;
	@JsonAlias("price")
	private Double basePrice;
	private Integer quantity=0;
	private Float tax=0.0f;

}
