package com.ecommerce.ajc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.ecommerce.ajc",       // ton backend-user
		"repository",              // ton package de repository dans ecommerce-core
		"model"                    // ton package d'entités dans ecommerce-core
})
@EnableJpaRepositories(basePackages = {
		"repository"               // permet à Spring de détecter ArticleRepository
})
@EntityScan(basePackages = {
		"model"                    // permet de détecter les entités comme Article
})
public class AjcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AjcApplication.class, args);
	}

}
