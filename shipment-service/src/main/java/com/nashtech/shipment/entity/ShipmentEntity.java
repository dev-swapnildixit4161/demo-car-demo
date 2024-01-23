package com.nashtech.shipment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class ShipmentEntity {
    @Id
    private String shipmentId;
    private String orderId;
    private String paymentId;

    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String emailId;
    private String mobileNumber;

    private String productId;
    private String brand;
    private Integer quantity;
    private Double basePrice;
    private Double subTotal;
    private Double total;
    private Float tax;
    private Float totalTax;

}
