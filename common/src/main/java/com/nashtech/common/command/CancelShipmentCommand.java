package com.nashtech.common.command;

import com.nashtech.common.model.ShipmentStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CancelShipmentCommand {
    @TargetAggregateIdentifier
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
    ShipmentStatus shipmentStatus = ShipmentStatus.SHIPMENT_CANCELLED;
}