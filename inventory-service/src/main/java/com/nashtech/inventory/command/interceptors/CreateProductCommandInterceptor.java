package com.nashtech.inventory.command.interceptors;

import com.nashtech.inventory.command.CreateProductCommand;
import com.nashtech.inventory.repository.ProductLookupEntity;
import com.nashtech.inventory.repository.ProductLookupRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;


@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
	private final ProductLookupRepository productLookupRepository;

	public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
		this.productLookupRepository = productLookupRepository;
	}

	@Nonnull
	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			@Nonnull List<? extends CommandMessage<?>> messages) {

		return (index, command) -> {

			log.info("Intercepted command: " + command.getPayloadType());

			if(CreateProductCommand.class.equals(command.getPayloadType())) {

				CreateProductCommand createProductCommand = (CreateProductCommand)command.getPayload();

				ProductLookupEntity productLookupEntity =  productLookupRepository.findByProductId(createProductCommand.getProductId());

				if(productLookupEntity != null) {
					throw new IllegalStateException(
							String.format("Product %s already exist", createProductCommand.getProductId()));
				}

			}

			return command;
		};
	}

}
