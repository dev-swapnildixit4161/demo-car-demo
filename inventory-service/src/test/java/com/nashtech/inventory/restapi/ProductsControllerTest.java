package com.nashtech.inventory.restapi;

import com.nashtech.inventory.query.FindProductsQuery;
import com.nashtech.inventory.query.ProductsSummary;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductsControllerTest {

    /**
     * Mocked instance of {@link QueryGateway} to simulate Axon query handling.
     */
    @Mock
    private QueryGateway queryGateway;

    /**
     * The instance of {@link ProductsController} under test.
     */
    @InjectMocks
    private ProductsController productsController;

    /**
     * Setup method to initialize Mockito annotations before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the {@link ProductsController#getProducts(String)} method.
     * It verifies that the controller correctly handles product queries and returns the expected result.
     */
    @Test
    void testGetProducts() {
        String productId = "123";
        ProductsSummary expectedProductsSummary = new ProductsSummary(/* provide necessary data */);
        CompletableFuture<ProductsSummary> queryResult = CompletableFuture.completedFuture(expectedProductsSummary);

        when(queryGateway.query(new FindProductsQuery(productId), ProductsSummary.class))
                .thenReturn(queryResult);

        ProductsSummary actualProductsSummary = productsController.getProducts(productId);

        assertEquals(expectedProductsSummary, actualProductsSummary);
    }

}