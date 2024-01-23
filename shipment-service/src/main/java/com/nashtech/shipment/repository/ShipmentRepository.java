package com.nashtech.shipment.repository;

import com.nashtech.shipment.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity, String> {
    ShipmentEntity findByOrderId(String orderId);

}
