package com.nashtech.order.commands;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
public class RejectOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
    String userId;
    String productId;
    String paymentId;
    String shipmentId;
    String reasonToFailed;
    OrderStatus orderStatus;
}
