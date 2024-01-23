package com.nashtech.order.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CompensateOrder {
    private String orderId;
    private String userId;
    private String productId;
    private String paymentId;
    private String shipmentId;
    private String reasonToFailed;
}
