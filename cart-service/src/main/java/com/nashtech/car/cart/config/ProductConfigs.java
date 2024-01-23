package com.nashtech.car.cart.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="inventory-service")
@Component
@Getter
@Setter
public class ProductConfigs {
    private String host;
    private String productUri;
}
