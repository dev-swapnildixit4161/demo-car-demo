package com.nashtech.car.cart.repository;

import com.nashtech.car.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProductIdAndUserId(String productId,String userId);
    List<CartItem> findByUserId(String userId);

}
