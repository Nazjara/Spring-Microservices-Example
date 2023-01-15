package com.nazjara.web;

import com.nazjara.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Component
public class BeerInventoryFailoverHandler {

    public Mono<ServerResponse> beerInventoryFailover(ServerRequest request) {
        log.debug("Beer inventory call failover requested");

        var response = Flux.just(BeerInventoryDto.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .beerId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .quantityOnHand(999)
                .build())
                .collectList();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, BeerInventoryDto.class);
    }
}
