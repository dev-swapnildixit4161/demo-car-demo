package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductReservedEvent {
    String orderId;
    String userId;
    String productId;
    Integer quantity;
    String brand;
    String model;
    Integer year;
    String color;
    Double mileage;
    Double basePrice;
    Double subTotal;
    Double total;
    Float totalTax;
    Float tax;

}