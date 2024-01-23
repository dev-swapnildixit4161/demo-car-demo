package com.nashtech.order.repository;

import com.nashtech.order.repository.entity.FailedOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedOrderRepository extends JpaRepository<FailedOrderEntity, String> {
    FailedOrderEntity findByOrderId(String orderId);
}
