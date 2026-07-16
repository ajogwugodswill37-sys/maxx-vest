package com.maxvest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.openapi.models.OpenAPI;
import org.springdoc.openapi.models.info.Info;
import org.springdoc.openapi.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Maxx Vest API")
                        .version("1.0.0")
                        .description("Production-grade Forex Trading Platform API")
                        .license(new License().name("MIT")));
    }
}
