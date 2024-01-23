package com.nashtech.inventory.events;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductCreatedEvent {
	String productId;
	String brand;
	String model;
	Integer year;
	String color;
	Double mileage;
	Double basePrice;
	Integer quantity;
	Float tax;

}
