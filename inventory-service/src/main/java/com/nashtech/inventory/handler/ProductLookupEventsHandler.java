package com.nashtech.inventory.handler;

import com.nashtech.inventory.events.ProductCreatedEvent;
import com.nashtech.inventory.repository.ProductLookupEntity;
import com.nashtech.inventory.repository.ProductLookupRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

	private final ProductLookupRepository productLookupRepository;

	public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
		this.productLookupRepository = productLookupRepository;
	}

	@EventHandler
	public void on(ProductCreatedEvent event) {
		productLookupRepository.save(new ProductLookupEntity(event.getProductId()));
	}


}
