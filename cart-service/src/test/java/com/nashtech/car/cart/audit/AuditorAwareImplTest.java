package com.nashtech.car.cart.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuditorAwareImpl.class})
@ExtendWith(SpringExtension.class)
class AuditorAwareImplTest {
    @Autowired
    private AuditorAwareImpl auditorAwareImpl;

    /**
     * Method under test: {@link AuditorAwareImpl#getCurrentAuditor()}
     */
    @Test
    void testGetCurrentAuditor() {
        // Arrange and Act
        Optional<String> actualCurrentAuditor = (new AuditorAwareImpl()).getCurrentAuditor();

        // Assert
        assertEquals("System", actualCurrentAuditor.get());
        assertTrue(actualCurrentAuditor.isPresent());
    }
}
