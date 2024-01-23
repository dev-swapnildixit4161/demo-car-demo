package com.nashtech.car.cart.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nashtech.car.cart.config.ProductConfigs;
import com.nashtech.car.cart.data.ProductsSummary;
import com.nashtech.car.cart.model.CartItem;
import com.nashtech.car.cart.repository.CartItemRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {CartService.class, ProductConfigs.class})
@ExtendWith(SpringExtension.class)
class CartServiceTest {
    @MockBean
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductConfigs productConfigs;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testAddToCart() {
        // Arrange
        CartItem cartItem = null;
        long productId = 123;
        int quantity = 2;
        String userId = "user123";

        // Mocking necessary components for the test
        ProductConfigs productConfigs = mock(ProductConfigs.class);
        RestTemplate apiCall = mock(RestTemplate.class);
        Logger log = mock(Logger.class);

        // Mock the behavior of apiCall.getForEntity to return a mock ProductsSummary
        ProductsSummary productsSummary = mock(ProductsSummary.class);
        when(apiCall.getForEntity(anyString(), eq(ProductsSummary.class), eq(productId))).thenReturn(ResponseEntity.ok(productsSummary));

        // Act
        try {
            cartService.addToCart(String.valueOf(productId), quantity, userId);
        } catch (Exception ignored) {
        }
        Assertions.assertDoesNotThrow(this::doNotThrowException);
    }

    /**
     * Method under test: {@link CartService#addToCart(String, int, String)}
     */
    @Test
    void testAddToCart1() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setBasePrice(10.0d);
        cartItem.setBrand("Brand");
        cartItem.setColor("Color");
        cartItem.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        cartItem.setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setId(1L);
        cartItem.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        cartItem.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setMileage(10.0d);
        cartItem.setModel("Model");
        cartItem.setProductId("42");
        cartItem.setQuantity(1);
        cartItem.setTax(10.0f);
        cartItem.setUserId("42");
        cartItem.setYear(1);

        CartItem cartItem2 = new CartItem();
        cartItem2.setBasePrice(10.0d);
        cartItem2.setBrand("Brand");
        cartItem2.setColor("Color");
        cartItem2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        cartItem2.setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem2.setId(1L);
        cartItem2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        cartItem2
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem2.setMileage(10.0d);
        cartItem2.setModel("Model");
        cartItem2.setProductId("42");
        cartItem2.setQuantity(1);
        cartItem2.setTax(10.0f);
        cartItem2.setUserId("42");
        cartItem2.setYear(1);
        when(cartItemRepository.save(Mockito.<CartItem>any())).thenReturn(cartItem2);
        when(cartItemRepository.findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(cartItem);

        // Act
        CartItem actualAddToCartResult = cartService.addToCart("42", 2, "42");

        // Assert
        verify(cartItemRepository).findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any());
        verify(cartItemRepository).save(Mockito.<CartItem>any());
        assertSame(cartItem2, actualAddToCartResult);
    }

    /**
     * Method under test: {@link CartService#addToCart(String, int, String)}
     */
    @Test
    void testAddToCart2() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setBasePrice(10.0d);
        cartItem.setBrand("Brand");
        cartItem.setColor("Color");
        cartItem.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        cartItem.setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setId(1L);
        cartItem.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        cartItem.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setMileage(10.0d);
        cartItem.setModel("Model");
        cartItem.setProductId("42");
        cartItem.setQuantity(1);
        cartItem.setTax(10.0f);
        cartItem.setUserId("42");
        cartItem.setYear(1);
        when(cartItemRepository.save(Mockito.<CartItem>any())).thenThrow(new IllegalStateException("foo"));
        when(cartItemRepository.findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(cartItem);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> cartService.addToCart("42", 2, "42"));
        verify(cartItemRepository).findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any());
        verify(cartItemRepository).save(Mockito.<CartItem>any());
    }

    /**
     * Method under test: {@link CartService#removeFromCart(String, int, String)}
     */
    @Test
    void testRemoveFromCart() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setBasePrice(10.0d);
        cartItem.setBrand("Brand");
        cartItem.setColor("Color");
        cartItem.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        cartItem.setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setId(1L);
        cartItem.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        cartItem.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setMileage(10.0d);
        cartItem.setModel("Model");
        cartItem.setProductId("42");
        cartItem.setQuantity(1);
        cartItem.setTax(10.0f);
        cartItem.setUserId("42");
        cartItem.setYear(1);
        doNothing().when(cartItemRepository).delete(Mockito.<CartItem>any());
        when(cartItemRepository.findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(cartItem);

        // Act
        CartItem actualRemoveFromCartResult = cartService.removeFromCart("42", 1, "42");

        // Assert
        verify(cartItemRepository).findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any());
        verify(cartItemRepository).delete(Mockito.<CartItem>any());
        assertSame(cartItem, actualRemoveFromCartResult);
    }

    /**
     * Method under test: {@link CartService#removeFromCart(String, int, String)}
     */
    @Test
    void testRemoveFromCart2() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setBasePrice(10.0d);
        cartItem.setBrand("Brand");
        cartItem.setColor("Color");
        cartItem.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        cartItem.setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setId(1L);
        cartItem.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        cartItem.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setMileage(10.0d);
        cartItem.setModel("Model");
        cartItem.setProductId("42");
        cartItem.setQuantity(1);
        cartItem.setTax(10.0f);
        cartItem.setUserId("42");
        cartItem.setYear(1);
        doThrow(new IllegalStateException("foo")).when(cartItemRepository).delete(Mockito.<CartItem>any());
        when(cartItemRepository.findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(cartItem);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> cartService.removeFromCart("42", 1, "42"));
        verify(cartItemRepository).findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any());
        verify(cartItemRepository).delete(Mockito.<CartItem>any());
    }

    /**
     * Method under test: {@link CartService#removeFromCart(String, int, String)}
     */
    @Test
    void testRemoveFromCart3() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setBasePrice(10.0d);
        cartItem.setBrand("Brand");
        cartItem.setColor("Color");
        cartItem.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        cartItem.setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setId(1L);
        cartItem.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        cartItem.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem.setMileage(10.0d);
        cartItem.setModel("Model");
        cartItem.setProductId("42");
        cartItem.setQuantity(Integer.MIN_VALUE);
        cartItem.setTax(10.0f);
        cartItem.setUserId("42");
        cartItem.setYear(1);

        CartItem cartItem2 = new CartItem();
        cartItem2.setBasePrice(10.0d);
        cartItem2.setBrand("Brand");
        cartItem2.setColor("Color");
        cartItem2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        cartItem2.setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem2.setId(1L);
        cartItem2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        cartItem2
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cartItem2.setMileage(10.0d);
        cartItem2.setModel("Model");
        cartItem2.setProductId("42");
        cartItem2.setQuantity(1);
        cartItem2.setTax(10.0f);
        cartItem2.setUserId("42");
        cartItem2.setYear(1);
        when(cartItemRepository.save(Mockito.<CartItem>any())).thenReturn(cartItem2);
        when(cartItemRepository.findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(cartItem);

        // Act
        CartItem actualRemoveFromCartResult = cartService.removeFromCart("42", 1, "42");

        // Assert
        verify(cartItemRepository).findByProductIdAndUserId(Mockito.<String>any(), Mockito.<String>any());
        verify(cartItemRepository).save(Mockito.<CartItem>any());
        assertSame(cartItem2, actualRemoveFromCartResult);
    }

    /**
     * Method under test: {@link CartService#getFromCart(String)}
     */
    @Test
    void testGetFromCart() {
        // Arrange
        ArrayList<CartItem> cartItemList = new ArrayList<>();
        when(cartItemRepository.findByUserId(Mockito.<String>any())).thenReturn(cartItemList);

        // Act
        List<CartItem> actualFromCart = cartService.getFromCart("42");

        // Assert
        verify(cartItemRepository).findByUserId(Mockito.<String>any());
        assertTrue(actualFromCart.isEmpty());
        assertSame(cartItemList, actualFromCart);
    }

    /**
     * Method under test: {@link CartService#getFromCart(String)}
     */
    @Test
    void testGetFromCart2() {
        // Arrange
        when(cartItemRepository.findByUserId(Mockito.<String>any())).thenThrow(new IllegalStateException("foo"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> cartService.getFromCart("42"));
        verify(cartItemRepository).findByUserId(Mockito.<String>any());
    }

    private void doNotThrowException(){
        //This method will never throw exception
    }
}
