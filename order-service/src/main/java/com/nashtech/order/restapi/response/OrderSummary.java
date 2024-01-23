package com.nashtech.order.restapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderSummary {
    private String orderId;
    private String orderStatus;
    private String message;

}