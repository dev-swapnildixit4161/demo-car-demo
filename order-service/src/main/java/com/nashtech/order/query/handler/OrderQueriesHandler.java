package com.nashtech.order.query.handler;

import com.nashtech.order.query.FindOrdersByUserQuery;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.OrderEntity;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderQueriesHandler {

    private final OrderRepository ordersRepository;

    public OrderQueriesHandler(OrderRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @QueryHandler
    public List<OrderEntity> findOrders(FindOrdersByUserQuery findOrdersByUserQuery) {
        return ordersRepository.findByUserId(findOrdersByUserQuery.getUserId());
    }

}
