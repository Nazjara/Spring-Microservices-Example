package com.nazjara.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancedRouteConfiguration {

    @Bean
    public RouteLocator loadBalancedRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/beer", "/api/v1/beer/*")
                        .uri("lb://beer-service")
                        .id("beer-service"))
                .route(r -> r.path("/api/v1/customers", "/api/v1/customers/**")
                        .uri("lb://beer-order-service")
                        .id("beer-order-service"))
                .route(r -> r.path("/api/v1/beer/*/inventory")
                        .filters(f -> f.circuitBreaker(c -> c
                                .setName("inventoryCircuitBreaker")
                                .setFallbackUri("forward:/beer-inventory-failover")
                                .setRouteId("beer-inventory-failover")))
                        .uri("lb://beer-inventory-service")
                        .id("beer-inventory-service"))
                .route(r -> r.path("/beer-inventory-failover/**")
                        .uri("lb://beer-inventory-failover-service")
                        .id("beer-inventory-failover-service"))
                .build();
    }
}
