package com.nashtech.common.command;

import com.nashtech.common.model.User;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class ShipmentOrderCommand {
    @TargetAggregateIdentifier
    String shipmentId;
    String orderId;
    String productId;
    Double price;
    User user;
    Integer quantity;

}