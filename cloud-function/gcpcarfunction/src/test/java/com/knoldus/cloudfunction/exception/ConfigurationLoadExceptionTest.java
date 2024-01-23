package com.knoldus.cloudfunction.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationLoadExceptionTest {

    @Test
    public void testNewConfigurationLoadException() {
        // Arrange
        String message = "An error occurred";
        Throwable cause = new Throwable();

        // Act
        ConfigurationLoadException actualConfigurationLoadException = new ConfigurationLoadException(message, cause);

        // Assert
        assertEquals("An error occurred", actualConfigurationLoadException.getLocalizedMessage());
        assertEquals("An error occurred", actualConfigurationLoadException.getMessage());
        Throwable cause2 = actualConfigurationLoadException.getCause();
        assertNull(cause2.getLocalizedMessage());
        assertNull(cause2.getMessage());
        assertNull(cause2.getCause());
        Throwable[] suppressed = actualConfigurationLoadException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertSame(cause, cause2);
        assertSame(suppressed, cause2.getSuppressed());
    }
}
