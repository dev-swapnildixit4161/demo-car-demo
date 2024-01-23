package com.nashtech.inventory.query;

import com.nashtech.inventory.exception.ProductNotFound;
import com.nashtech.inventory.repository.ProductEntity;
import com.nashtech.inventory.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductsQueryHandlerTest {

    /**
     * Test case to verify the correct behavior of the {@code findProducts} method
     * when a valid {@code FindProductsQuery} is provided, and the corresponding product exists.
     */
    @Test
    void test_valid_findProductsQuery() {
        ProductsRepository productsRepository = mock(ProductsRepository.class);
        ProductsQueryHandler productsQueryHandler = new ProductsQueryHandler(productsRepository);
        FindProductsQuery query = new FindProductsQuery("123");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId("123");
        productEntity.setBrand("Brand");
        productEntity.setModel("Model");
        productEntity.setYear(2021);
        productEntity.setColor("Red");
        productEntity.setMileage(1000.0);
        productEntity.setBasePrice(10000.0);
        productEntity.setQuantity(5);
        productEntity.setTax(0.1f);

        when(productsRepository.findByProductId("123")).thenReturn(productEntity);

        ProductsSummary result = productsQueryHandler.findProducts(query);

        assertNotNull(result);
        assertEquals("123", result.getProductId());
        assertEquals("Brand", result.getBrand());
        assertEquals("Model", result.getModel());
        assertEquals(Integer.valueOf(2021), result.getYear());
        assertEquals("Red", result.getColor());
        assertEquals(Double.valueOf(1000.0), result.getMileage());
        assertEquals(Double.valueOf(10000.0), result.getBasePrice());
        assertEquals(Integer.valueOf(5), result.getQuantity());
        assertEquals(Float.valueOf(0.1f), result.getTax());
    }

    /**
     * Test case to ensure that a {@code ProductNotFound} exception is thrown
     * when attempting to find a product with a non-existing product ID.
     */
    @Test
    void test_nonExistingProductId_findProductsQuery() {
        ProductsRepository productsRepository = mock(ProductsRepository.class);
        ProductsQueryHandler productsQueryHandler = new ProductsQueryHandler(productsRepository);
        FindProductsQuery query = new FindProductsQuery("123");

        when(productsRepository.findByProductId("123")).thenReturn(null);

        assertThrows(ProductNotFound.class, () -> productsQueryHandler.findProducts(query));
    }

    /**
     * Test case to confirm that a {@code NullPointerException} is thrown
     * when attempting to find products with a null {@code FindProductsQuery}.
     */
    @Test
    void test_nullFindProductsQuery_findProducts() {
        ProductsRepository productsRepository = mock(ProductsRepository.class);
        ProductsQueryHandler productsQueryHandler = new ProductsQueryHandler(productsRepository);

        assertThrows(NullPointerException.class, () -> productsQueryHandler.findProducts(null));
    }

    /**
     * Test case to verify that a {@code ProductNotFound} exception is thrown
     * when attempting to find products with a valid query, but the corresponding product entity is null.
     */
    @Test
    void test_nullProductEntity_findProducts() {
        ProductsRepository productsRepository = mock(ProductsRepository.class);
        ProductsQueryHandler productsQueryHandler = new ProductsQueryHandler(productsRepository);
        FindProductsQuery query = new FindProductsQuery("123");

        when(productsRepository.findByProductId("123")).thenReturn(null);

        assertThrows(ProductNotFound.class, () -> productsQueryHandler.findProducts(query));
    }
}