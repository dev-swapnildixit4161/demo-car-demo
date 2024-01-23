package com.nashtech.common.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class CancelProductReserveCommand {
    @TargetAggregateIdentifier
    String productId;
    Integer quantity;
    String orderId;
    String userId;
    String reasonToFailed;

}
