package com.nashtech.order;

import com.nashtech.order.commands.handler.CreateOrderCommandInterceptor;
import com.nashtech.order.repository.OrderLookupRepository;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.test.utils.RecordingCommandBus;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.mockito.Mockito.*;

public class OrderApplicationTest {
  /**
   * Method under test:
   * {@link OrderApplication#registerCreateProductCommandInterceptor(ApplicationContext, CommandBus)}
   */
  @Test
  public void testRegisterCreateProductCommandInterceptor() throws BeansException {
    // Arrange
    OrderApplication orderApplication = new OrderApplication();
    AnnotationConfigApplicationContext context = mock(AnnotationConfigApplicationContext.class);
    when(context.getBean(Mockito.<Class<CreateOrderCommandInterceptor>>any()))
            .thenReturn(new CreateOrderCommandInterceptor(mock(OrderLookupRepository.class)));

    // Act
    orderApplication.registerCreateProductCommandInterceptor(context, new RecordingCommandBus());

    // Assert that nothing has changed
    verify(context).getBean(Mockito.<Class<CreateOrderCommandInterceptor>>any());
  }

}
