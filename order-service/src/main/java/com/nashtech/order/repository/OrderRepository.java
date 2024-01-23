package com.nashtech.order.repository;

import com.nashtech.order.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByUserId(String userId);
    OrderEntity findByOrderId(String orderId);
}
