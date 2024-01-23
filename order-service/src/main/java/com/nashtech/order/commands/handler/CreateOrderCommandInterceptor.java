package com.nashtech.order.commands.handler;

import com.nashtech.order.commands.CreateOrderCommand;
import com.nashtech.order.repository.OrderLookupRepository;
import com.nashtech.order.repository.entity.OrderLookupEntity;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CreateOrderCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final OrderLookupRepository orderLookupRepository;

    public CreateOrderCommandInterceptor(OrderLookupRepository productLookupRepository) {
        this.orderLookupRepository = productLookupRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {

        return (index, command) -> {

            log.info("Intercepted command: " + command.getPayloadType());

            if (CreateOrderCommand.class.equals(command.getPayloadType())) {
                CreateOrderCommand createOrderCommand = (CreateOrderCommand) command.getPayload();
                Optional<OrderLookupEntity> orderLookup = orderLookupRepository.findById(createOrderCommand.getOrderId());
                if (orderLookup.isPresent()) {
                    throw new IllegalStateException(String.format("Order already %s already exist",
                            createOrderCommand.getOrderId()));
                }
            }
            return command;
        };
    }

}