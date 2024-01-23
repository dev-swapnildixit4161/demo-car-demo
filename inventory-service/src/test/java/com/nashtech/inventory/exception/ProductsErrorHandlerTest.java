package com.nashtech.inventory.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link ProductsErrorHandler} class.
 */
class ProductsErrorHandlerTest {

    /**
     * Test case to verify the behavior of handling other exceptions.
     * It checks if the error handler properly generates an internal server error response
     * with the correct status code, timestamp, and exception message.
     */
    @Test
    void test_handleOtherExceptions_exception() {
        Exception ex = new Exception("Test Exception");
        ProductsErrorHandler errorHandler = new ProductsErrorHandler();
        ResponseEntity<Object> response = errorHandler.handleOtherExceptions(ex);
        ErrorMessage errorMessage = (ErrorMessage) response.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(errorMessage);
        assertEquals(new Date().getTime(), errorMessage.getTimestamp().getTime(), 1000);
        assertEquals("Test Exception", errorMessage.getMessage());
    }

    /**
     * Test case to verify the HTTP status code when handling other exceptions.
     * It checks if the error handler returns the expected internal server error status code.
     */
    @Test
    void test_handleOtherExceptions_httpStatus() {
        Exception ex = new Exception("Test Exception");
        ProductsErrorHandler errorHandler = new ProductsErrorHandler();
        ResponseEntity<Object> response = errorHandler.handleOtherExceptions(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}