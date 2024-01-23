package com.nashtech.inventory.command.interceptors;

import com.nashtech.inventory.command.CreateProductCommand;
import com.nashtech.inventory.repository.ProductLookupEntity;
import com.nashtech.inventory.repository.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for the {@link CreateProductCommandInterceptor} class, which serves as an interceptor
 * for handling and validating {@link CreateProductCommand} instances before they are processed.
 * The tests cover various scenarios such as successful interception, exception throwing for existing products,
 * and handling cases where the product does not exist.
 */
class CreateProductCommandInterceptorTest {

    /**
     * Test case to verify the successful interception of a {@link CreateProductCommand} instance.
     */
    @Test
    void test_interceptCreateProductCommand() {
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("123")
                .brand("Brand")
                .model("Model")
                .year(2021)
                .color("Color")
                .mileage(1000.0)
                .basePrice(10000.0)
                .quantity(1)
                .tax(0.1f)
                .build();
        CommandMessage<CreateProductCommand> commandMessage = new GenericCommandMessage<>(createProductCommand);

        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        CommandMessage<?> interceptedCommand = result.apply(0, commandMessage);

        assertEquals(commandMessage, interceptedCommand);
    }

    /**
     * Test case to ensure that an {@link IllegalStateException} is thrown
     * when attempting to create a product that already exists.
     */
    @Test
    void test_throwExceptionIfProductExists() {
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("123")
                .brand("Brand")
                .model("Model")
                .year(2021)
                .color("Color")
                .mileage(1000.0)
                .basePrice(10000.0)
                .quantity(1)
                .tax(0.1f)
                .build();
        CommandMessage<CreateProductCommand> commandMessage = new GenericCommandMessage<>(createProductCommand);
        ProductLookupEntity existingProduct = new ProductLookupEntity();
        when(productLookupRepository.findByProductId(createProductCommand.getProductId())).thenReturn(existingProduct);

        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        assertThrows(IllegalStateException.class, () -> result.apply(0, commandMessage));
    }

    /**
     * Test case to confirm that the interceptor returns the original {@link CreateProductCommand}
     * if the product with the specified ID does not exist.
     */
    @Test
    void test_returnCommandIfProductDoesNotExist() {
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("123")
                .brand("Brand")
                .model("Model")
                .year(2021)
                .color("Color")
                .mileage(1000.0)
                .basePrice(10000.0)
                .quantity(1)
                .tax(0.1f)
                .build();
        CommandMessage<CreateProductCommand> commandMessage = new GenericCommandMessage<>(createProductCommand);
        when(productLookupRepository.findByProductId(createProductCommand.getProductId())).thenReturn(null);

        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        CommandMessage<?> interceptedCommand = result.apply(0, commandMessage);
        assertEquals(commandMessage, interceptedCommand);
    }

    /**
     * Test case to verify that the interceptor handles scenarios where there is no
     * {@link CreateProductCommand} in the list of messages.
     */
    @Test
    void test_noCreateProductCommandInMessages() {
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CommandMessage<?> commandMessage = new GenericCommandMessage<>(new Object());

        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        CommandMessage<?> interceptedCommand = result.apply(0, commandMessage);
        assertEquals(commandMessage, interceptedCommand);
    }

    /**
     * Test case to ensure that the interceptor correctly handles a scenario where
     * the {@link ProductLookupRepository} returns null for a given product ID.
     */
    @Test
    void test_productLookupRepositoryReturnsNull() {
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("123")
                .brand("Brand")
                .model("Model")
                .year(2021)
                .color("Color")
                .mileage(1000.0)
                .basePrice(10000.0)
                .quantity(1)
                .tax(0.1f)
                .build();
        CommandMessage<CreateProductCommand> commandMessage = new GenericCommandMessage<>(createProductCommand);
        when(productLookupRepository.findByProductId(createProductCommand.getProductId())).thenReturn(null);

        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        CommandMessage<?> interceptedCommand = result.apply(0, commandMessage);
        assertEquals(commandMessage, interceptedCommand);
    }
}
