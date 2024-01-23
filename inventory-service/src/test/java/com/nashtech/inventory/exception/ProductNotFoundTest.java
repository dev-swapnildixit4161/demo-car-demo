package com.nashtech.inventory.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for the {@link ProductNotFound} exception.
 * It verifies the behavior of the exception when created with a custom message.
 */
class ProductNotFoundTest {

    /**
     * Tests the creation of a new {@link ProductNotFound} instance with a custom message.
     */
    @Test
    void testNewProductNotFound() {
        ProductNotFound actualProductNotFound = new ProductNotFound("Not all who wander are lost");

        assertEquals("Not all who wander are lost", actualProductNotFound.getLocalizedMessage());
        assertEquals("Not all who wander are lost", actualProductNotFound.getMessage());
        assertNull(actualProductNotFound.getCause());
        assertEquals(0, actualProductNotFound.getSuppressed().length);
    }
}
