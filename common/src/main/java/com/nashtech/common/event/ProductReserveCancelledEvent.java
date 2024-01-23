package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductReserveCancelledEvent {
    String productId;
    String orderId;
    String userId;
    Integer quantity;

}
