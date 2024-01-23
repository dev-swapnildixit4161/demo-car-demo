package com.nashtech.common.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class ReserveProductCommand {
    @TargetAggregateIdentifier
    String productId;
    String orderId;
    String userId;
    Integer quantity;

}
