package com.nashtech.inventory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.nashtech.inventory.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class ProductSubscriberServiceTest {

    /**
     * Mocked instance of {@link CommandGateway} for testing.
     */
    @Mock
    private CommandGateway commandGateway;

    /**
     * Mocked instance of {@link ObjectMapper} for testing.
     */
    @Mock
    private ObjectMapper objectMapper;

    /**
     * Mocked instance of {@link BasicAcknowledgeablePubsubMessage} for testing.
     */
    @Mock
    private BasicAcknowledgeablePubsubMessage message;

    /**
     * Instance of {@link ProductSubscriberService} to be tested.
     */
    @InjectMocks
    private ProductSubscriberService productSubscriberService;

    /**
     * Sets up the testing environment by initializing mock objects.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for successful parsing of the message payload in {@link ProductSubscriberService#messageReceiver(String, BasicAcknowledgeablePubsubMessage)}.
     *
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    @Test
    void messageReceiver_SuccessfulParsing() throws JsonProcessingException {
        String payload = "{\"productId\":1,\"brand\":\"Brand\",\"model\":\"Model\",\"year\":2022,\"color\":\"Red\",\"mileage\":100,\"basePrice\":10000,\"quantity\":5,\"tax\":0.1}";
        ProductRequest productRequest = new ProductRequest();  // Assuming you have a default constructor for ProductRequest

        when(objectMapper.readValue(payload, ProductRequest.class)).thenReturn(productRequest);

        productSubscriberService.messageReceiver(payload, message);

        verify(message).ack();

        verify(commandGateway).send(any(CreateProductCommand.class));
    }

    /**
     * Test case for failed parsing of the message payload in {@link ProductSubscriberService#messageReceiver(String, BasicAcknowledgeablePubsubMessage)}.
     *
     * @throws JsonProcessingException If an error occurs during JSON processing.
     */
    @Test
    void messageReceiver_FailedParsing() throws JsonProcessingException {
        String payload = "invalid_payload";
        when(objectMapper.readValue(payload, ProductRequest.class)).thenThrow(new JsonProcessingException("Error") {});

        productSubscriberService.messageReceiver(payload, message);

        verify(message, never()).ack();

        verify(commandGateway, never()).send(any(CreateProductCommand.class));
    }
}