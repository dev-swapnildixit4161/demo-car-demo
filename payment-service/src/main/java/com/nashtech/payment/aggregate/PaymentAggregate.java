package com.nashtech.payment.aggregate;

import com.nashtech.common.command.ProcessPaymentCommand;
import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.common.event.PaymentCancelledEvent;
import com.nashtech.common.model.PaymentDetails;
import com.nashtech.common.model.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@Getter
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String productId;
    private Integer quantity;
    private User user;
    private Double basePrice;

    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) {
        log.info("ProcessPaymentCommand started");
        User paymentUser = getUser();
        PaymentDetails paymentDetails = getPaymentDetails();

        if (!processPaymentCommand.getUserId().equals(paymentUser.getUserId())
                && paymentDetails.getUserId().equals(paymentUser.getUserId())) {
            AggregateLifecycle.apply(buildPaymentCancelEvent(processPaymentCommand,"User does not exist"));
            return;
        }

        if (paymentDetails.getBalanceAmount() <= processPaymentCommand.getTotal()) {
            AggregateLifecycle.apply(buildPaymentCancelEvent(processPaymentCommand,"Insufficient Amount"));
            return;
        }

        PaymentApprovedEvent paymentApprovedEvent = PaymentApprovedEvent.builder()
                        .paymentId(processPaymentCommand.getPaymentId())
                        .orderId(processPaymentCommand.getOrderId())
                        .productId(processPaymentCommand.getProductId())
                        .user(paymentUser)
                        .quantity(processPaymentCommand.getQuantity())
                        .brand(processPaymentCommand.getBrand())
                        .subTotal(processPaymentCommand.getSubTotal())
                        .total(processPaymentCommand.getTotal())
                        .tax(processPaymentCommand.getTax())
                        .totalTax(processPaymentCommand.getTotalTax())
                        .basePrice(processPaymentCommand.getBasePrice())
                        .build();
        AggregateLifecycle.apply(paymentApprovedEvent);
    }

    @EventSourcingHandler
    public void on(PaymentApprovedEvent paymentApprovedEvent) {
        this.paymentId = paymentApprovedEvent.getPaymentId();
        this.orderId = paymentApprovedEvent.getOrderId();
        this.productId = paymentApprovedEvent.getProductId();
        this.quantity = paymentApprovedEvent.getQuantity();
        this.user = paymentApprovedEvent.getUser();
    }

    @EventSourcingHandler
    public void on(PaymentCancelledEvent paymentApprovedEvent) {
        this.paymentId = paymentApprovedEvent.getPaymentId();
        this.orderId = paymentApprovedEvent.getOrderId();
        this.productId = paymentApprovedEvent.getProductId();
        this.quantity = paymentApprovedEvent.getQuantity();
    }

    //Hard coded User details
    public User getUser() {
        return User.builder()
                .userId("1652")
                .firstName("Abid")
                .lastName("Khan")
                .address("Noida")
                .mobileNumber("9087658765")
                .emailId("abid.khan@nashtechglobal.com")
                .build();
    }

    //Hard coded payment details
    public PaymentDetails getPaymentDetails() {
        return PaymentDetails.builder()
                .userId("1652")
                .bank("SBI")
                .cardNumber("0900987654435443")
                .validUntilYear(2028)
                .validUntilMonth(6)
                .cvv(334)
                .balanceAmount(1000000000d) //1arab
                .build();
    }

    public PaymentCancelledEvent buildPaymentCancelEvent(ProcessPaymentCommand processPaymentCommand, String reasonToFailed) {
        return PaymentCancelledEvent.builder()
                .paymentId(processPaymentCommand.getPaymentId())
                .orderId(processPaymentCommand.getOrderId())
                .userId(processPaymentCommand.getUserId())
                .productId(processPaymentCommand.getProductId())
                .quantity(processPaymentCommand.getQuantity())
                .reasonToFailed(reasonToFailed)
                .build();
    }

}
