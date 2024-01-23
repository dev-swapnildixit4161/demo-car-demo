package com.nashtech.order.config;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CorsConfigTest {
    /**
     * Method under test: {@link CorsConfig#addCorsMappings(CorsRegistry)}
     */
    @Test
    void testAddCorsMappings() {
        CorsConfig corsConfig = new CorsConfig();
        CorsRegistry registry = mock(CorsRegistry.class);
        when(registry.addMapping(Mockito.<String>any())).thenReturn(new CorsRegistration("Path Pattern"));

        // Act
        corsConfig.addCorsMappings(registry);

        // Assert that nothing has changed
        verify(registry).addMapping(Mockito.<String>any());
    }
}
