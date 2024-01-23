package com.nashtech.order.commands;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
public class ApproveOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
    String paymentId;
    String shipmentId;
    OrderStatus orderStatus;
}
