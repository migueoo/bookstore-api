package com.bookstoreapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@OpenAPIDefinition(info = @Info(title = "Your Supplier Application", version = "1.0.0"))
public class BookstoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApiApplication.class, args);
	}

}
