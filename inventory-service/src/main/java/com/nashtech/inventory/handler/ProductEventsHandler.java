package com.nashtech.inventory.handler;


import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.events.ProductCreatedEvent;
import com.nashtech.inventory.repository.ProductEntity;
import com.nashtech.inventory.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("product-group")
@Slf4j
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;

    public ProductEventsHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productsRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        log.info("ProductReservedEvent: Request quantity {}", productReservedEvent.getQuantity());
        ProductEntity productEntity = productsRepository.findByProductId(productReservedEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
        productsRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReserveCancelledEvent productReservationCancelledEvent) {
        log.info("ProductReservationCancelledEvent: Revert product {} quantity {}",
                productReservationCancelledEvent.getQuantity(), productReservationCancelledEvent.getProductId());

        ProductEntity currentlyStoredProductEntity = productsRepository.findByProductId(productReservationCancelledEvent.getProductId());
        currentlyStoredProductEntity.setQuantity(currentlyStoredProductEntity.getQuantity() +
                productReservationCancelledEvent.getQuantity());

        productsRepository.save(currentlyStoredProductEntity);
    }

}
