package com.nashtech.order.commands;

import com.nashtech.order.commands.handler.CreateOrderCommandInterceptor;
import com.nashtech.order.repository.OrderLookupRepository;
import io.axoniq.axonserver.grpc.command.Command;
import org.axonframework.axonserver.connector.command.GrpcBackedCommandMessage;
import org.axonframework.axonserver.connector.event.axon.GrpcMetaDataAwareSerializer;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.serialization.SerializedType;
import org.axonframework.serialization.Serializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CreateOrderCommandInterceptor.class})
@ExtendWith(SpringExtension.class)
class CreateOrderCommandInterceptorTest {
    @Autowired
    private CreateOrderCommandInterceptor createOrderCommandInterceptor;

    @MockBean
    private OrderLookupRepository orderLookupRepository;

    /**
     * Method under test: {@link CreateOrderCommandInterceptor#handle(List)}
     */
    @Test
    void testCreateOrderCommandInterceptorHandle() {
        // Arrange
        Serializer delegate = mock(Serializer.class);
        Class<Object> forNameResult = Object.class;
        when(delegate.classForType(Mockito.<SerializedType>any())).thenReturn(forNameResult);
        GrpcMetaDataAwareSerializer serializer = new GrpcMetaDataAwareSerializer(delegate);
        GrpcBackedCommandMessage<?> grpcBackedCommandMessage = new GrpcBackedCommandMessage<>(Command.getDefaultInstance(),
                serializer);

        ArrayList<CommandMessage<?>> messages = new ArrayList<>();
        messages.add(grpcBackedCommandMessage);

        // Act
        createOrderCommandInterceptor.handle(messages);

        // Assert
        verify(delegate).classForType(Mockito.<SerializedType>any());
    }
}
