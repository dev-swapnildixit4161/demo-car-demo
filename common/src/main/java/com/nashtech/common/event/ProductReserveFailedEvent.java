package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductReserveFailedEvent {
    String orderId;
    String userId;
    String productId;
    String reasonToFailed;
}
