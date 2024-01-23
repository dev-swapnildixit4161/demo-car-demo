package com.nashtech.order.commands;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
    String productId;
    Integer quantity;
    String userId;

}