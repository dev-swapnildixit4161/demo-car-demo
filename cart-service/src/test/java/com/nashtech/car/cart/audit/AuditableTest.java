package com.nashtech.car.cart.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import com.nashtech.car.cart.model.CartItem;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

class AuditableTest {
    /**
     * Method under test: {@link Auditable#getCreatedBy()}
     */
    @Test
    void testGetCreatedBy() {
        // Arrange, Act and Assert
        assertNull((new CartItem()).getCreatedBy());
    }

    /**
     * Method under test: {@link Auditable#getCreatedBy()}
     */
    @Test
    void testGetCreatedBy2() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setCreatedDate(mock(Date.class));

        // Act and Assert
        assertNull(cartItem.getCreatedBy());
    }

    /**
     * Method under test: {@link Auditable#getCreatedDate()}
     */
    @Test
    void testGetCreatedDate() {
        // Arrange, Act and Assert
        assertNull((new CartItem()).getCreatedDate());
    }

    /**
     * Method under test: {@link Auditable#getCreatedDate()}
     */
    @Test
    void testGetCreatedDate2() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setCreatedDate(mock(java.sql.Date.class));

        // Act and Assert
        assertSame(cartItem.createdDate, cartItem.getCreatedDate());
    }

    /**
     * Method under test: {@link Auditable#getLastModifiedBy()}
     */
    @Test
    void testGetLastModifiedBy() {
        // Arrange, Act and Assert
        assertNull((new CartItem()).getLastModifiedBy());
    }

    /**
     * Method under test: {@link Auditable#getLastModifiedBy()}
     */
    @Test
    void testGetLastModifiedBy2() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setCreatedDate(mock(Date.class));

        // Act and Assert
        assertNull(cartItem.getLastModifiedBy());
    }

    /**
     * Method under test: {@link Auditable#getLastModifiedDate()}
     */
    @Test
    void testGetLastModifiedDate() {
        // Arrange, Act and Assert
        assertNull((new CartItem()).getLastModifiedDate());
    }

    /**
     * Method under test: {@link Auditable#getLastModifiedDate()}
     */
    @Test
    void testGetLastModifiedDate2() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setCreatedDate(mock(java.sql.Date.class));

        // Act and Assert
        assertNull(cartItem.getLastModifiedDate());
    }

    /**
     * Method under test: {@link Auditable#setCreatedBy(Object)}
     */
    @Test
    void testSetCreatedBy() {
        // Arrange
        CartItem cartItem = new CartItem();

        // Act
        cartItem.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");

        // Assert
        assertEquals("Jan 1, 2020 8:00am GMT+0100", cartItem.getCreatedBy());
    }

    /**
     * Method under test: {@link Auditable#setCreatedBy(Object)}
     */
    @Test
    void testSetCreatedBy2() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setCreatedDate(mock(Date.class));

        // Act
        cartItem.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");

        // Assert
        assertEquals("Jan 1, 2020 8:00am GMT+0100", cartItem.getCreatedBy());
    }

    /**
     * Method under test: {@link Auditable#setCreatedDate(Date)}
     */
    @Test
    void testSetCreatedDate() {
        // Arrange
        CartItem cartItem = new CartItem();
        java.util.Date createdDate = java.util.Date
                .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        cartItem.setCreatedDate(createdDate);

        // Assert
        assertSame(createdDate, cartItem.getCreatedDate());
    }

    /**
     * Method under test: {@link Auditable#setCreatedDate(java.util.Date)}
     */
    @Test
    void testSetCreatedDate2() {
        // Arrange
        CartItem cartItem = new CartItem();
        java.sql.Date createdDate = mock(java.sql.Date.class);

        // Act
        cartItem.setCreatedDate(createdDate);

        // Assert
        assertSame(createdDate, cartItem.getCreatedDate());
    }

    /**
     * Method under test: {@link Auditable#setLastModifiedBy(Object)}
     */
    @Test
    void testSetLastModifiedBy() {
        // Arrange
        CartItem cartItem = new CartItem();

        // Act
        cartItem.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");

        // Assert
        assertEquals("Jan 1, 2020 9:00am GMT+0100", cartItem.getLastModifiedBy());
    }

    /**
     * Method under test: {@link Auditable#setLastModifiedBy(Object)}
     */
    @Test
    void testSetLastModifiedBy2() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setCreatedDate(mock(Date.class));

        // Act
        cartItem.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");

        // Assert
        assertEquals("Jan 1, 2020 9:00am GMT+0100", cartItem.getLastModifiedBy());
    }

    /**
     * Method under test: {@link Auditable#setLastModifiedDate(Date)}
     */
    @Test
    void testSetLastModifiedDate() {
        // Arrange
        CartItem cartItem = new CartItem();
        java.util.Date lastModifiedDate = java.util.Date
                .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        cartItem.setLastModifiedDate(lastModifiedDate);

        // Assert
        assertSame(lastModifiedDate, cartItem.getLastModifiedDate());
    }

    /**
     * Method under test: {@link Auditable#setLastModifiedDate(java.util.Date)}
     */
    @Test
    void testSetLastModifiedDate2() {
        // Arrange
        CartItem cartItem = new CartItem();
        java.sql.Date lastModifiedDate = mock(java.sql.Date.class);

        // Act
        cartItem.setLastModifiedDate(lastModifiedDate);

        // Assert
        assertSame(lastModifiedDate, cartItem.getLastModifiedDate());
    }
}
