package com.nashtech.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {
	ProductEntity findByProductId(String productId);

}
