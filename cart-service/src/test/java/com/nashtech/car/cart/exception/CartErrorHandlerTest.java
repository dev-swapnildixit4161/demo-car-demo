package com.nashtech.car.cart.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

class CartErrorHandlerTest {
    /**
     * Method under test:
     * {@link CartErrorHandler#handleIllegalStateException(IllegalStateException, WebRequest)}
     */
    @Test
    void testHandleIllegalStateException() {
        // Arrange
        CartErrorHandler cartErrorHandler = new CartErrorHandler();
        IllegalStateException ex = new IllegalStateException("foo");

        // Act
        ResponseEntity<Object> actualHandleIllegalStateExceptionResult = cartErrorHandler.handleIllegalStateException(ex,
                new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("foo", ((ErrorMessage) actualHandleIllegalStateExceptionResult.getBody()).getMessage());
        assertEquals(500, actualHandleIllegalStateExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleIllegalStateExceptionResult.hasBody());
        assertTrue(actualHandleIllegalStateExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CartErrorHandler#handleIllegalStateException(IllegalStateException, WebRequest)}
     */
    @Test
    void testHandleIllegalStateException2() {
        // Arrange
        CartErrorHandler cartErrorHandler = new CartErrorHandler();
        IllegalStateException ex = new IllegalStateException("42");

        // Act
        ResponseEntity<Object> actualHandleIllegalStateExceptionResult = cartErrorHandler.handleIllegalStateException(ex,
                new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("42", ((ErrorMessage) actualHandleIllegalStateExceptionResult.getBody()).getMessage());
        assertEquals(500, actualHandleIllegalStateExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleIllegalStateExceptionResult.hasBody());
        assertTrue(actualHandleIllegalStateExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link CartErrorHandler#handleOtherExceptions(Exception, WebRequest)}
     */
    @Test
    void testHandleOtherExceptions() {
        // Arrange
        CartErrorHandler cartErrorHandler = new CartErrorHandler();
        Exception ex = new Exception("foo");

        // Act
        ResponseEntity<Object> actualHandleOtherExceptionsResult = cartErrorHandler.handleOtherExceptions(ex,
                new ServletWebRequest(new MockHttpServletRequest()));

        // Assert
        assertEquals("foo", ((ErrorMessage) actualHandleOtherExceptionsResult.getBody()).getMessage());
        assertEquals(500, actualHandleOtherExceptionsResult.getStatusCodeValue());
        assertTrue(actualHandleOtherExceptionsResult.hasBody());
        assertTrue(actualHandleOtherExceptionsResult.getHeaders().isEmpty());
    }
}
