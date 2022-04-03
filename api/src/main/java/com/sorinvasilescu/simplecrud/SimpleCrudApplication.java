package com.sorinvasilescu.simplecrud;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SimpleCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleCrudApplication.class, args);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Simple Crud Example")
                .description("Simple Crud example application")
                .version("v0.0.1")
                .license(new License().name("GNU GPL v3").url("https://github.com/sorinvasilescu/simple-crud/blob/main/LICENSE")))
            .externalDocs(new ExternalDocumentation()
                .description("Simple Crud Documentation")
                .url("https://github.com/sorinvasilescu/simple-crud/wiki"))
            .components(
                new Components()
                    .addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                            .name("bearerAuth")
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            );
    }
}
