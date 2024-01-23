package com.nashtech.car.cart.service;

import com.nashtech.car.cart.config.ProductConfigs;
import com.nashtech.car.cart.data.ProductsSummary;
import com.nashtech.car.cart.model.CartItem;
import com.nashtech.car.cart.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CartService {

    private final CartItemRepository cartItemRepository;

    private final RestTemplate apiCall;

    private final ProductConfigs productConfigs;

    public CartService(CartItemRepository cartItemRepository, RestTemplate apiCall, ProductConfigs productConfigs) {
        this.cartItemRepository = cartItemRepository;
        this.apiCall = apiCall;
        this.productConfigs = productConfigs;
    }

    // @Transactional
    public CartItem addToCart(String productId, int quantity, String userId) {

        CartItem cartItem = cartItemRepository.findByProductIdAndUserId(productId, userId);

        if (Objects.isNull(cartItem)) {
            ProductsSummary productsSummary;
            try {
                productsSummary = apiCall.getForEntity(productConfigs.getHost() + productConfigs.getProductUri(),
                        ProductsSummary.class, productId).getBody();
            } catch (Exception ex) {
                log.error("Unable to process add to cart due to {} ", ex.getMessage());
                throw ex;
            }
            cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(userId);
            cartItem.setBasePrice(productsSummary.getBasePrice());
            cartItem.setMileage(productsSummary.getMileage());
            cartItem.setModel(productsSummary.getModel());
            cartItem.setColor(productsSummary.getColor());
            cartItem.setBrand(productsSummary.getBrand());
            cartItem.setTax(productsSummary.getTax());
            cartItem.setYear(productsSummary.getYear());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        return cartItemRepository.save(cartItem);
    }

    public CartItem removeFromCart(String productId, int quantity, String userId) {
        CartItem cartItem = cartItemRepository.findByProductIdAndUserId(productId, userId);

        if (cartItem != null) {
            int updatedQuantity = cartItem.getQuantity() - quantity;
            if (updatedQuantity <= 0) {
                cartItemRepository.delete(cartItem);
            } else {
                cartItem.setQuantity(updatedQuantity);
                cartItem = cartItemRepository.save(cartItem);
            }
        }
        return cartItem;
    }

    @Transactional
    public List<CartItem> getFromCart(String userId) {
        List<CartItem> cartItem = cartItemRepository.findByUserId(userId);
        if (cartItem == null) {
            throw new IllegalStateException("Product not found from the cart");
        }
        return cartItem;
    }

}
