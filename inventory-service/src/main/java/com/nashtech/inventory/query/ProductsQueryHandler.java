package com.nashtech.inventory.query;

import com.nashtech.inventory.exception.ProductNotFound;
import com.nashtech.inventory.repository.ProductEntity;
import com.nashtech.inventory.repository.ProductsRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductsQueryHandler {

	private final ProductsRepository productsRepository;

	public ProductsQueryHandler(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

	@QueryHandler
	public ProductsSummary findProducts(FindProductsQuery query) {
		ProductEntity product = productsRepository.findByProductId(query.getProductId());
		if (Objects.isNull(product)) {
			throw new ProductNotFound(String.format("Product [%s] does not exist", query.getProductId()));
		}
		ProductsSummary productsSummary = new ProductsSummary();
		BeanUtils.copyProperties(product, productsSummary);
		return productsSummary;
	}

}
