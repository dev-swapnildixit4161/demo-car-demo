package com.nashtech.order.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "failed_orders")
public class FailedOrderEntity {
    @Id
    private String orderId;
    private String userId;
    private String productId;
    private String paymentId;
    private String shipmentId;
    private Date timestamp;
    private String reasonToFailed;
    private String orderStatus;
}
