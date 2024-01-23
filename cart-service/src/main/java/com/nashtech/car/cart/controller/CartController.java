package com.nashtech.car.cart.controller;

import com.nashtech.car.cart.model.CartItem;
import com.nashtech.car.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
    @Autowired // NOSONAR
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestParam String productId, @RequestParam int quantity,
                                            @RequestParam String userId) {
        return new ResponseEntity<>(cartService.addToCart(productId, quantity,userId), HttpStatus.CREATED);

    }

    @PostMapping("/remove")
    public ResponseEntity<CartItem> removeFromCart(@RequestParam String productId, @RequestParam int quantity,
                                                 @RequestParam String userId) {
        return new ResponseEntity<>(cartService.removeFromCart(productId,quantity,userId),HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CartItem>> getFromCart(@RequestParam String userId) {
        return new ResponseEntity<>(cartService.getFromCart(userId),HttpStatus.OK);
    }

}