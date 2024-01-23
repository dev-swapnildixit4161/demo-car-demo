package com.nashtech.payment.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.nashtech.common.command.ProcessPaymentCommand;
import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.common.event.PaymentCancelledEvent;
import com.nashtech.common.model.PaymentDetails;
import com.nashtech.common.model.User;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentAggregateTest {

    private AggregateTestFixture<PaymentAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(PaymentAggregate.class);
    }

    @Test
    void testShipmentAggregateCreation() {

        ProcessPaymentCommand processPaymentCommand = ProcessPaymentCommand.builder()
                .basePrice(10.0d)
                .brand("Brand")
                .orderId("42")
                .paymentId("42")
                .productId("42")
                .quantity(1)
                .subTotal(10.0d)
                .tax(10.0f)
                .total(10.0d)
                .totalTax(10.0f)
                .userId("1652")
                .build();

        PaymentApprovedEvent paymentApprovedEvent = PaymentApprovedEvent.builder()
                .basePrice(10.0d)
                .brand("Brand")
                .orderId("42")
                .paymentId("42")
                .productId("42")
                .quantity(1)
                .subTotal(10.0d)
                .tax(10.0f)
                .total(10.0d)
                .totalTax(10.0f)
                .user(User.builder()
                        .userId("1652")
                        .firstName("Abid")
                        .lastName("Khan")
                        .address("Noida")
                        .emailId("abid.khan@nashtechglobal.com")
                        .mobileNumber("9087658765")
                        .build()).build();

        fixture.givenNoPriorActivity()
                .when(processPaymentCommand)
                .expectEvents(paymentApprovedEvent);
    }

    /**
     * Method under test:
     * {@link PaymentAggregate#buildPaymentCancelEvent(ProcessPaymentCommand, String)}
     */
    @Test
    void testBuildPaymentCancelEvent() {
        // Arrange
        PaymentAggregate paymentAggregate = new PaymentAggregate();
        ProcessPaymentCommand processPaymentCommand = ProcessPaymentCommand.builder()
                .basePrice(10.0d)
                .brand("Brand")
                .orderId("42")
                .paymentId("42")
                .productId("42")
                .quantity(1)
                .subTotal(10.0d)
                .tax(10.0f)
                .total(10.0d)
                .totalTax(10.0f)
                .userId("42")
                .build();

        // Act
        PaymentCancelledEvent actualBuildPaymentCancelEventResult = paymentAggregate
                .buildPaymentCancelEvent(processPaymentCommand, "Just cause");

        // Assert
        assertEquals("42", actualBuildPaymentCancelEventResult.getOrderId());
        assertEquals("42", actualBuildPaymentCancelEventResult.getPaymentId());
        assertEquals("42", actualBuildPaymentCancelEventResult.getProductId());
        assertEquals("42", actualBuildPaymentCancelEventResult.getUserId());
        assertEquals("Just cause", actualBuildPaymentCancelEventResult.getReasonToFailed());
        assertEquals(1, actualBuildPaymentCancelEventResult.getQuantity().intValue());
    }

    /**
     * Method under test: {@link PaymentAggregate#getPaymentDetails()}
     */
    @Test
    void testGetPaymentDetails() {
        // Arrange and Act
        PaymentDetails actualPaymentDetails = (new PaymentAggregate()).getPaymentDetails();

        // Assert
        assertEquals("0900987654435443", actualPaymentDetails.getCardNumber());
        assertEquals("1652", actualPaymentDetails.getUserId());
        assertEquals("SBI", actualPaymentDetails.getBank());
        assertEquals(1.0E9d, actualPaymentDetails.getBalanceAmount().doubleValue());
        assertEquals(2028, actualPaymentDetails.getValidUntilYear().intValue());
        assertEquals(334, actualPaymentDetails.getCvv().intValue());
        assertEquals(6, actualPaymentDetails.getValidUntilMonth().intValue());
    }

    /**
     * Method under test: {@link PaymentAggregate#getUser()}
     */
    @Test
    void testGetUser() {
        // Arrange and Act
        User actualUser = (new PaymentAggregate()).getUser();

        // Assert
        assertEquals("1652", actualUser.getUserId());
        assertEquals("9087658765", actualUser.getMobileNumber());
        assertEquals("Abid", actualUser.getFirstName());
        assertEquals("Khan", actualUser.getLastName());
        assertEquals("Noida", actualUser.getAddress());
        assertEquals("abid.khan@nashtechglobal.com", actualUser.getEmailId());
    }

    /**
     * Method under test: {@link PaymentAggregate#PaymentAggregate()}
     */
    @Test
    void testNewPaymentAggregate() {
        // Arrange and Act
        PaymentAggregate actualPaymentAggregate = new PaymentAggregate();

        // Assert
        PaymentDetails paymentDetails = actualPaymentAggregate.getPaymentDetails();
        assertEquals("0900987654435443", paymentDetails.getCardNumber());
        assertEquals("1652", paymentDetails.getUserId());
        User user = actualPaymentAggregate.getUser();
        assertEquals("1652", user.getUserId());
        assertEquals("9087658765", user.getMobileNumber());
        assertEquals("Abid", user.getFirstName());
        assertEquals("Khan", user.getLastName());
        assertEquals("Noida", user.getAddress());
        assertEquals("SBI", paymentDetails.getBank());
        assertEquals("abid.khan@nashtechglobal.com", user.getEmailId());
        assertNull(actualPaymentAggregate.getBasePrice());
        assertNull(actualPaymentAggregate.getQuantity());
        assertNull(actualPaymentAggregate.getOrderId());
        assertNull(actualPaymentAggregate.getPaymentId());
        assertNull(actualPaymentAggregate.getProductId());
        assertEquals(1.0E9d, paymentDetails.getBalanceAmount().doubleValue());
        assertEquals(2028, paymentDetails.getValidUntilYear().intValue());
        assertEquals(334, paymentDetails.getCvv().intValue());
        assertEquals(6, paymentDetails.getValidUntilMonth().intValue());
    }

    /**
     * Method under test: {@link PaymentAggregate#on(PaymentApprovedEvent)}
     */
    @Test
    void testOn() {
        // Arrange
        PaymentAggregate paymentAggregate = new PaymentAggregate();
        PaymentApprovedEvent.PaymentApprovedEventBuilder totalTaxResult = PaymentApprovedEvent.builder()
                .basePrice(10.0d)
                .brand("Brand")
                .orderId("42")
                .paymentId("42")
                .productId("42")
                .quantity(1)
                .subTotal(10.0d)
                .tax(10.0f)
                .total(10.0d)
                .totalTax(10.0f);
        User user = User.builder().emailId("42").firstName("Jane").lastName("Doe").mobileNumber("42").userId("42").build();
        PaymentApprovedEvent paymentApprovedEvent = totalTaxResult.user(user).build();

        // Act
        paymentAggregate.on(paymentApprovedEvent);

        // Assert
        assertEquals("42", paymentAggregate.getOrderId());
        assertEquals("42", paymentAggregate.getPaymentId());
        assertEquals("42", paymentAggregate.getProductId());
        assertEquals(1, paymentAggregate.getQuantity().intValue());
    }

    /**
     * Method under test: {@link PaymentAggregate#on(PaymentCancelledEvent)}
     */
    @Test
    void testOn2() {
        // Arrange
        PaymentAggregate paymentAggregate = new PaymentAggregate();
        PaymentCancelledEvent paymentApprovedEvent = PaymentCancelledEvent.builder()
                .orderId("42")
                .paymentId("42")
                .productId("42")
                .quantity(1)
                .reasonToFailed("Just cause")
                .userId("42")
                .build();

        // Act
        paymentAggregate.on(paymentApprovedEvent);

        // Assert
        assertEquals("42", paymentAggregate.getOrderId());
        assertEquals("42", paymentAggregate.getPaymentId());
        assertEquals("42", paymentAggregate.getProductId());
        assertEquals(1, paymentAggregate.getQuantity().intValue());
    }
}
