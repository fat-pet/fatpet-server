package com.example.fatpetserver.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    fun swaggerApi(): OpenAPI = OpenAPI()
        .components(Components())
        .info(
            Info()
                .title("Fatpet API Documentation")
                .description("Fatpet API 문서")
                .version("0.0.1")
        )
}
