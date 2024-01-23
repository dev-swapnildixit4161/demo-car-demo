package com.nashtech.inventory;

import com.nashtech.inventory.command.interceptors.CreateProductCommandInterceptor;
import com.nashtech.inventory.exception.ProductsServiceEventsErrorHandler;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@AllArgsConstructor
public class InventoryApplication {


	private CommandGateway commandGateway;

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
	@Autowired
	public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
	}

	@Autowired
	public void configure(EventProcessingConfigurer config) {
		config.registerListenerInvocationErrorHandler("product-group", conf -> new ProductsServiceEventsErrorHandler());
	}

}
