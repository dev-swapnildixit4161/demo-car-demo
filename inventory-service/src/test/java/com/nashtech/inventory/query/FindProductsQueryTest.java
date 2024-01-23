package com.nashtech.inventory.query;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FindProductsQueryTest {

    /**
     * Test case for a valid product ID.
     * Verifies that the FindProductsQuery is created successfully
     * and the provided product ID can be retrieved.
     */
    @Test
    void test_valid_productId() {
        String productId = "123";
        FindProductsQuery query = new FindProductsQuery(productId);
        assertNotNull(query);
        assertEquals(productId, query.getProductId());
    }

    /**
     * Test case for a null product ID.
     * Verifies that the FindProductsQuery is created successfully
     * and the product ID remains null.
     */
    @Test
    void test_null_productId() {
        FindProductsQuery query = new FindProductsQuery(null);
        assertNotNull(query);
        assertNull(query.getProductId());
    }

    /**
     * Test case for an empty product ID.
     * Verifies that the FindProductsQuery is created successfully
     * and the provided empty product ID can be retrieved.
     */
    @Test
    void test_empty_productId() {
        String productId = "";
        FindProductsQuery query = new FindProductsQuery(productId);
        assertNotNull(query);
        assertEquals(productId, query.getProductId());
    }
}