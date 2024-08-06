package com.dev.template.controller;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Paths;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(Arrays.asList(
                    new Server().url("http://mydormroom.ddns.net:10802/vscode-server/backend").description("Staging"),
                    new Server().url("https://prod.example.com").description("Production"),
                    new Server().url("http://host.docker.internal:10802/vscode-server/backend").description("Internal")
                ))
                .components(new Components()
                        .addSecuritySchemes("bearerToken",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                        )
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerToken")
                        )
                .info(new Info().title("Spring Boot REST API")
                        .description("Spring Boot REST API with UUID and Token headers")
                        .version("1.0"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("**")
                .build();
    }
}