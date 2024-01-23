package com.nashtech.payment.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<PaymentEntity,String> {

    PaymentEntity findByOrderId(String orderId);

}
