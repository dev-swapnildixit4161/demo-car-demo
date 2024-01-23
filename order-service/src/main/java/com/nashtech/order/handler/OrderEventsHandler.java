package com.nashtech.order.handler;

import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.repository.FailedOrderRepository;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.FailedOrderEntity;
import com.nashtech.order.repository.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@ProcessingGroup("order-group")
@Slf4j
public class OrderEventsHandler {

    private final OrderRepository orderRepository;
    private final FailedOrderRepository failedOrderRepository;

    public OrderEventsHandler(OrderRepository orderRepository, FailedOrderRepository failedOrderRepository) {
        this.orderRepository = orderRepository;
        this.failedOrderRepository = failedOrderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderEntity order = new OrderEntity(event.getOrderId(), event.getUserId(), event.getProductId(),
                null, null, new Date(),event.getOrderStatus().toString());
        orderRepository.save(order);

        FailedOrderEntity failedOrder = new FailedOrderEntity();
        failedOrder.setOrderId(event.getOrderId());
        failedOrder.setTimestamp(new Date());
        failedOrderRepository.save(failedOrder);
    }

    @EventHandler
    public void on(OrderApprovedEvent orderApprovedEvent) {
        OrderEntity order = orderRepository.findByOrderId((orderApprovedEvent.getOrderId()));
        order.setPaymentId(orderApprovedEvent.getPaymentId());
        order.setShipmentId(orderApprovedEvent.getShipmentId());
        order.setOrderStatus(orderApprovedEvent.getOrderStatus().toString());
        orderRepository.save(order);

        FailedOrderEntity failedOrder = new FailedOrderEntity();
        failedOrder.setOrderId(orderApprovedEvent.getOrderId());
        failedOrderRepository.delete(failedOrder);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        Optional<FailedOrderEntity> orderOptional = failedOrderRepository.findById((event.getOrderId()));
        if(orderOptional.isEmpty()) {
            log.error("Order failed  status did not persist {}",event.getOrderId());
            return;
        }
        FailedOrderEntity failedOrder = orderOptional.get();
        failedOrder.setProductId(event.getProductId());
        failedOrder.setPaymentId(event.getPaymentId());
        failedOrder.setShipmentId(event.getShipmentId());
        failedOrder.setUserId(event.getUserId());
        failedOrder.setOrderStatus(event.getOrderStatus().toString());
        failedOrder.setReasonToFailed(event.getReasonToFailed());
        failedOrderRepository.save(failedOrder);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        log.error("{} occurred during order saving", exception.getMessage());
        throw exception;
    }

}
