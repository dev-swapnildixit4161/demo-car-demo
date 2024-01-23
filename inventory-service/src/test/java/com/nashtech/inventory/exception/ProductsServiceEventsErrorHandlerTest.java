package com.nashtech.inventory.exception;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for the {@link ProductsServiceEventsErrorHandler} class.
 */
class ProductsServiceEventsErrorHandlerTest {

    /**
     * Test to ensure that the {@link ProductsServiceEventsErrorHandler} correctly throws the same exception
     * that occurred during event processing.
     */
    @Test
    void test_throws_same_exception() {
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new Exception();
        EventMessage<?> event = mock(EventMessage.class);
        EventMessageHandler eventHandler = mock(EventMessageHandler.class);

        assertThrows(Exception.class, () -> errorHandler.onError(exception, event, eventHandler));
    }

    /**
     * Test to ensure that the {@link ProductsServiceEventsErrorHandler} correctly throws any exception
     * that occurred during event processing, specifically a {@link RuntimeException}.
     */
    @Test
    void test_throws_any_exception() {
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new RuntimeException();
        EventMessage<?> event = mock(EventMessage.class);
        EventMessageHandler eventHandler = mock(EventMessageHandler.class);

        assertThrows(RuntimeException.class, () -> errorHandler.onError(exception, event, eventHandler));
    }

    /**
     * Test to ensure that the {@link ProductsServiceEventsErrorHandler} correctly throws an exception
     * when the event being processed is null.
     */
    @Test
    void test_throws_exception_when_event_is_null() {
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new Exception();
        EventMessageHandler eventHandler = mock(EventMessageHandler.class);

        assertThrows(Exception.class, () -> errorHandler.onError(exception, null, eventHandler));
    }

    /**
     * Test to ensure that the {@link ProductsServiceEventsErrorHandler} correctly throws an exception
     * when the event handler is null.
     */
    @Test
    void test_throws_exception_when_event_handler_is_null() {
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new Exception();
        EventMessage<?> event = mock(EventMessage.class);

        assertThrows(Exception.class, () -> errorHandler.onError(exception, event, null));
    }

    /**
     * Test to ensure that the {@link ProductsServiceEventsErrorHandler} correctly throws an exception
     * when both the event and event handler are null.
     */
    @Test
    void test_throws_exception_when_both_event_and_event_handler_are_null() {
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new Exception();

        assertThrows(Exception.class, () -> errorHandler.onError(exception, null, null));
    }
}