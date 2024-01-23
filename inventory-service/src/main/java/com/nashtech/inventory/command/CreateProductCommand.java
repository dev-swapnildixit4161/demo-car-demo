package com.nashtech.inventory.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Value
public class CreateProductCommand {
	@TargetAggregateIdentifier
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
