package com.ecommerce.ajc.config;

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
