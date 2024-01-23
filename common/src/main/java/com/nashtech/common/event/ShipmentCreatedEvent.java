package com.nashtech.common.event;

import com.nashtech.common.model.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ShipmentCreatedEvent {
    String shipmentId;
    String paymentId;
    String orderId;
    User user;
    String productId;
    String brand;
    Double subTotal;
    Double total;
    Float tax;
    Float totalTax;
    Double basePrice;
    Integer quantity;

}
