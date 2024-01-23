package com.nashtech.order.events;

import com.nashtech.common.utils.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCreatedEvent {
    String orderId;
    String productId;
    Integer quantity;
    String userId;
    OrderStatus orderStatus;

}
