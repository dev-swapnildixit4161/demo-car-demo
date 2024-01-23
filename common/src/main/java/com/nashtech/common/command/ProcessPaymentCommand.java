package com.nashtech.common.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class ProcessPaymentCommand {
    @TargetAggregateIdentifier
    String paymentId;
    String orderId;
    String productId;
    String userId;
    Integer quantity;
    String brand;
    Float tax;
    Double basePrice;
    Double subTotal;
    Double total;
    Float totalTax;

}