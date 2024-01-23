package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderShippedEvent {
    String orderId;
    String paymentId;
    String shipmentId;
}
