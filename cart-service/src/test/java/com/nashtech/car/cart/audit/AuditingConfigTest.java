package com.nashtech.car.cart.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class AuditingConfigTest {
    /**
     * Method under test: {@link AuditingConfig#auditorProvider()}
     */
    @Test
    void testAuditorProvider() {
        // Arrange and Act
        Optional<String> actualCurrentAuditor = (new AuditingConfig()).auditorProvider().getCurrentAuditor();

        // Assert
        assertEquals("System", actualCurrentAuditor.get());
        assertTrue(actualCurrentAuditor.isPresent());
    }
}
