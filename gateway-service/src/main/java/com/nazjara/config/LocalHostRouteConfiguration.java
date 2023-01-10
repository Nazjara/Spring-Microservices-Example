package com.nazjara.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalHostRouteConfiguration {

    @Bean
    public RouteLocator localhostRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/beer", "/api/v1/beer/*")
                        .uri("http://localhost:8080")
                        .id("beer-service"))
                .route(r -> r.path("/api/v1/customers", "/api/v1/customers/**")
                        .uri("http://localhost:8081")
                        .id("order-service"))
                .route(r -> r.path("/api/v1/beer/*/inventory")
                        .uri("http://localhost:8082")
                        .id("inventory-service"))
                .build();
    }
}