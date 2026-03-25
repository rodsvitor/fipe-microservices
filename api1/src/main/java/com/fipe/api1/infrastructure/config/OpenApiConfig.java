package com.fipe.api1.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("FIPE API")
            .version("v1")
            .description("""
                Vehicle API documentation.
                
                🔐 Authentication:
                1. Call /auth/login
                2. Copy the token
                3. Click 'Authorize'
                4. Paste: Bearer <token>
                """
            )
        )
        .components(new Components()
            .addSecuritySchemes("bearerAuth",
                new SecurityScheme()
                    .name("Authorization")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )
        );
  }

//  @Bean
//  public GroupedOpenApi authApi() {
//    return GroupedOpenApi.builder()
//        .group("auth")
//        .pathsToMatch("/auth/**")
//        .build();
//  }
//
//  @Bean
//  public GroupedOpenApi loadApi() {
//    return GroupedOpenApi.builder()
//        .group("load")
//        .pathsToMatch("/load/**")
//        .build();
//  }
//
//  @Bean
//  public GroupedOpenApi vehicleApi() {
//    return GroupedOpenApi.builder()
//        .group("vehicles")
//        .pathsToMatch("/v1/vehicles/**")
//        .build();
//  }

}
