package com.nashtech.payment.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class PaymentEntity {

    @Id
    private String  paymentId;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double basePrice;
    private Double subTotal;
    private Double total;
    private Float tax;
    Float totalTax;

}
