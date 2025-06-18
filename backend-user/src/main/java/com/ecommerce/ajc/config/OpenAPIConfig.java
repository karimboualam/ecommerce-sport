package com.ecommerce.ajc.config;

import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI ecommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Sport API")
                        .description("Documentation de l’API pour la gestion du site e-commerce de sport")
                        .version("1.0.0"));
    }
}
