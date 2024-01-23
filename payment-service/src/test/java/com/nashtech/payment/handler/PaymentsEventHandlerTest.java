package com.nashtech.payment.handler;

import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.payment.data.PaymentEntity;
import com.nashtech.payment.data.PaymentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@Slf4j
class PaymentsEventHandlerTest {
    @Mock
    private PaymentsRepository paymentsRepository;

    private PaymentsEventHandler paymentsEventHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentsEventHandler = new PaymentsEventHandler(paymentsRepository);
    }

    @Test
    void testPaymentApprovedEventHandling() {
        // Given
        PaymentApprovedEvent paymentApprovedEvent = PaymentApprovedEvent.builder().build();

        // When
        paymentsEventHandler.on(paymentApprovedEvent);

        // Then
        Mockito.verify(paymentsRepository, Mockito.times(1)).save(Mockito.any(PaymentEntity.class));
    }
}
