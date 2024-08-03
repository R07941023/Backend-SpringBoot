package com.dev.template.controller;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(Arrays.asList(
                    new Server().url("http://mydormroom.ddns.net:10802/vscode-server/backend").description("Staging"),
                    new Server().url("https://prod.example.com").description("Production"),
                    new Server().url("http://host.docker.internal:10802/vscode-server/backend").description("Internal")
                ));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("**")
                .build();
    }
}