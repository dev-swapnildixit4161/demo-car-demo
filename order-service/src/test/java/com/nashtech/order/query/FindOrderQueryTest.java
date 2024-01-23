package com.nashtech.order.query;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FindOrderQueryTest {
    /**
     * Method under test: {@link FindOrderQuery#FindOrderQuery()}
     */
    @Test
    void testNewFindOrderQuery() {
        // Arrange, Act and Assert
        assertNull((new FindOrderQuery()).orderId);
        assertEquals("42", (new FindOrderQuery("42")).orderId);
    }
}
