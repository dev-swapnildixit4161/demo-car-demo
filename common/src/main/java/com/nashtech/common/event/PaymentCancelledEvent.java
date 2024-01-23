package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentCancelledEvent {
    String orderId;
    String paymentId;
    Integer quantity;
    String userId;
    String reasonToFailed;
    String productId;

}
