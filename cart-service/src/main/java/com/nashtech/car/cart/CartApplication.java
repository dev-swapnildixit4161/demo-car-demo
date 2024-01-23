package com.nashtech.car.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan (basePackages = "com.nashtech.car.cart")
public class CartApplication {
	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

}