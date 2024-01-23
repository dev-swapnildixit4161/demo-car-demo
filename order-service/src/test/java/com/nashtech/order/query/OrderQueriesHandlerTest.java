package com.nashtech.order.query;

import com.nashtech.order.query.handler.OrderQueriesHandler;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.OrderEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderQueriesHandler.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderQueriesHandlerTest {
    @Autowired
    private OrderQueriesHandler orderQueriesHandler;

    @MockBean
    private OrderRepository orderRepository;

    /**
     * Method under test:
     * {@link OrderQueriesHandler#findOrders(FindOrdersByUserQuery)}
     */
    @Test
    public void testFindOrders() {
        // Arrange
        ArrayList<OrderEntity> orderEntityList = new ArrayList<>();
        when(orderRepository.findByUserId(Mockito.<String>any())).thenReturn(orderEntityList);

        // Act
        List<OrderEntity> actualFindOrdersResult = orderQueriesHandler.findOrders(new FindOrdersByUserQuery("42"));

        // Assert
        verify(orderRepository).findByUserId(Mockito.<String>any());
        assertTrue(actualFindOrdersResult.isEmpty());
        assertSame(orderEntityList, actualFindOrdersResult);
    }
}
