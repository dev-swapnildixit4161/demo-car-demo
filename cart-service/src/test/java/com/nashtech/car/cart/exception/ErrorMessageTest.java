package com.nashtech.car.cart.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class ErrorMessageTest {
    /**
     * Method under test: {@link ErrorMessage#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ErrorMessage(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                "Not all who wander are lost")).canEqual("Other"));
        assertFalse((new ErrorMessage(mock(java.sql.Date.class), "Not all who wander are lost")).canEqual("Other"));
    }

    /**
     * Method under test: {@link ErrorMessage#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                "Not all who wander are lost");

        // Act and Assert
        assertTrue(errorMessage.canEqual(
                new ErrorMessage(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                        "Not all who wander are lost")));
    }

    /**
     * Method under test: {@link ErrorMessage#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(null,
                new ErrorMessage(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                        "Not all who wander are lost"));
        assertNotEquals("Different type to ErrorMessage",
                new ErrorMessage(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                        "Not all who wander are lost"));
    }

    /**
     * Method under test: {@link ErrorMessage#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(
                Date.from(LocalDate.now().atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), "Not all who wander are lost");

        // Act and Assert
        assertNotEquals(errorMessage,
                new ErrorMessage(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                        "Not all who wander are lost"));
    }

    /**
     * Method under test: {@link ErrorMessage#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(null, "Not all who wander are lost");

        // Act and Assert
        assertNotEquals(errorMessage,
                new ErrorMessage(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                        "Not all who wander are lost"));
    }

    /**
     * Method under test: {@link ErrorMessage#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), "Message");

        // Act and Assert
        assertNotEquals(errorMessage,
                new ErrorMessage(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                        "Not all who wander are lost"));
    }

    /**
     * Method under test: {@link ErrorMessage#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);

        // Act and Assert
        assertNotEquals(errorMessage,
                new ErrorMessage(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                        "Not all who wander are lost"));
    }

    /**
     * Method under test: {@link ErrorMessage#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(mock(java.sql.Date.class), "Not all who wander are lost");

        // Act and Assert
        assertNotEquals(errorMessage,
                new ErrorMessage(
                        java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                        "Not all who wander are lost"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorMessage#equals(Object)}
     *   <li>{@link ErrorMessage#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                "Not all who wander are lost");

        // Act and Assert
        assertEquals(errorMessage, errorMessage);
        int expectedHashCodeResult = errorMessage.hashCode();
        assertEquals(expectedHashCodeResult, errorMessage.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorMessage#equals(Object)}
     *   <li>{@link ErrorMessage#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                "Not all who wander are lost");
        ErrorMessage errorMessage2 = new ErrorMessage(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                "Not all who wander are lost");

        // Act and Assert
        assertEquals(errorMessage, errorMessage2);
        int expectedHashCodeResult = errorMessage.hashCode();
        assertEquals(expectedHashCodeResult, errorMessage2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorMessage#equals(Object)}
     *   <li>{@link ErrorMessage#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(null, "Not all who wander are lost");
        ErrorMessage errorMessage2 = new ErrorMessage(null, "Not all who wander are lost");

        // Act and Assert
        assertEquals(errorMessage, errorMessage2);
        int expectedHashCodeResult = errorMessage.hashCode();
        assertEquals(expectedHashCodeResult, errorMessage2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorMessage#equals(Object)}
     *   <li>{@link ErrorMessage#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        ErrorMessage errorMessage = new ErrorMessage(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);
        ErrorMessage errorMessage2 = new ErrorMessage(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), null);

        // Act and Assert
        assertEquals(errorMessage, errorMessage2);
        int expectedHashCodeResult = errorMessage.hashCode();
        assertEquals(expectedHashCodeResult, errorMessage2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorMessage#ErrorMessage(Date, String)}
     *   <li>{@link ErrorMessage#toString()}
     *   <li>{@link ErrorMessage#getMessage()}
     *   <li>{@link ErrorMessage#getTimestamp()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Date timestamp = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        ErrorMessage actualErrorMessage = new ErrorMessage(timestamp, "Not all who wander are lost");
        actualErrorMessage.toString();
        String actualMessage = actualErrorMessage.getMessage();

        // Assert
        assertEquals("Not all who wander are lost", actualMessage);
        assertSame(timestamp, actualErrorMessage.getTimestamp());
    }
}
