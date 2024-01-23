package com.nashtech.car.cart.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.nashtech.car.cart.model.CartItem;
import com.nashtech.car.cart.service.CartService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
class CartControllerTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartService cartService;

    /**
     * Method under test: {@link CartController#addToCart(String, int, String)}
     */
    @Test
    void testAddToCart() throws Exception {
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
        when(cartService.addToCart(Mockito.<String>any(), anyInt(), Mockito.<String>any())).thenReturn(cartItem);
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.post("/cart/add").param("productId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("quantity", String.valueOf(1))
                .param("userId", "foo");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cartController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"createdDate\":0,\"lastModifiedBy\":\"Jan 1, 2020 9:00am"
                                        + " GMT+0100\",\"lastModifiedDate\":0,\"id\":1,\"productId\":\"42\",\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"color"
                                        + "\":\"Color\",\"mileage\":10.0,\"basePrice\":10.0,\"quantity\":1,\"tax\":10.0,\"userId\":\"42\"}"));
    }

    /**
     * Method under test: {@link CartController#removeFromCart(String, int, String)}
     */
    @Test
    void testRemoveFromCart() throws Exception {
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
        when(cartService.removeFromCart(Mockito.<String>any(), anyInt(), Mockito.<String>any())).thenReturn(cartItem);
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.post("/cart/remove").param("productId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("quantity", String.valueOf(1))
                .param("userId", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"createdDate\":0,\"lastModifiedBy\":\"Jan 1, 2020 9:00am"
                                        + " GMT+0100\",\"lastModifiedDate\":0,\"id\":1,\"productId\":\"42\",\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"color"
                                        + "\":\"Color\",\"mileage\":10.0,\"basePrice\":10.0,\"quantity\":1,\"tax\":10.0,\"userId\":\"42\"}"));
    }

    /**
     * Method under test: {@link CartController#getFromCart(String)}
     */
    @Test
    void testGetFromCart() throws Exception {
        // Arrange
        when(cartService.getFromCart(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/get").param("userId", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
