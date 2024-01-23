package com.nashtech.common.command;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class ApproveOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
    String paymentId;
    String shipmentId;
    OrderStatus orderStatus;

}