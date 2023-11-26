package com.salesianos.triana.appbike.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BikeApp Spring Boot 3 API -------")
                        .version("1.0")
                        .description("App for Renting Bikes Spring Boot 3 with Swagger")
                        .license(new License().name("Salesianos Triana Junior Fullstack Developers").url("https://github.com/Junior-FullStacks-By-Salesianos/appbike-back.git")));
    }

}
