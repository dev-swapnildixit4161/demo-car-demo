package com.nashtech.order.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {

    @NotBlank(message = "ProductId is a required field")
    private String productId;

    @Min(value = 1, message = "Quantity cannot be lower than 1")
    private Integer quantity;

    @NotBlank(message = "UserId is a required field")
    private String userId;

}