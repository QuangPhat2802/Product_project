package com.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.demo")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.demo")
@EntityScan(basePackages = "com.demo")
public class ProductProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductProjectApplication.class, args);
	}

}
