package com.nazjara.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("discovery-disabled")
public class LocalHostRouteConfiguration {

    @Bean
    public RouteLocator localhostRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/beer", "/api/v1/beer/*")
                        .uri("http://localhost:8080")
                        .id("beer-beer-service"))
                .route(r -> r.path("/api/v1/customers", "/api/v1/customers/**")
                        .uri("http://localhost:8081")
                        .id("beer-order-service"))
                .route(r -> r.path("/api/v1/beer/*/inventory")
                        .uri("http://localhost:8082")
                        .id("beer-inventory-service"))
                .build();
    }
}
