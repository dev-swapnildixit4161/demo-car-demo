package com.nashtech.common.event;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ShipmentCancelledEvent {
    String shipmentId;
    String orderId;
    String productId;
    Integer quantity;
    Double price;
    Double subTotal;
    Double grandTotal;
    Float tax;
    String userId;
    String firstName;
    String lastName;
    String address;
    String reasonToFailed;
    String paymentId;
    ShipmentStatus shipmentStatus;
}