package com.example.fatpetserver.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun swaggerApi(): OpenAPI =
        OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    "JWT",
                    SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"),
                ),
            ).addSecurityItem(
                SecurityRequirement()
                    .addList("JWT"),
            ).info(
                Info()
                    .title("Fatpet API Documentation")
                    .description("팻펫 API 문서")
                    .version("0.0.0"),
            )
}
