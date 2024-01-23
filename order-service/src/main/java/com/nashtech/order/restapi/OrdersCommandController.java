package com.nashtech.order.restapi;


import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.commands.CreateOrderCommand;
import com.nashtech.order.query.FindOrdersByUserQuery;
import com.nashtech.order.repository.entity.OrderEntity;
import com.nashtech.order.restapi.request.OrderCreateRequest;
import com.nashtech.order.restapi.response.OrderSummary;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrdersCommandController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    public OrdersCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/create")
    public OrderSummary createOrder(@Valid @RequestBody OrderCreateRequest orderRequest) {
        String orderId = UUID.randomUUID().toString();
        log.info("Order {} created request ", orderId);
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .productId(orderRequest.getProductId()).userId(orderRequest.getUserId())
                .quantity(orderRequest.getQuantity()).orderId(orderId)
                .build();

        commandGateway.send(createOrderCommand);
        return OrderSummary.builder()
                .orderId(orderId)
                .message("Thank you for your order! We’ll let you know as soon as it ships. " +
                        "You can track your order here,review us here, or shop again here.")
                .orderStatus(OrderStatus.ORDER_PLACED.toString())
                .build();
    }

    @GetMapping("/{userId}")
    public List<OrderEntity> getOrdersByUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
        log.info("Get orders status by userId: {}", userId);
        return queryGateway.query(new FindOrdersByUserQuery(userId), ResponseTypes.multipleInstancesOf(OrderEntity.class)).get();
    }
}