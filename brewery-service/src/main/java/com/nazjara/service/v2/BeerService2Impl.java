package com.nazjara.service.v2;

import com.nazjara.dto.v2.BeerDto2;
import com.nazjara.dto.v2.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BeerService2Impl implements BeerService2 {

    @Override
    public BeerDto2 get(UUID id) {
        return BeerDto2.builder().id(UUID.randomUUID())
                .name("Galaxy Cat")
                .style(BeerStyle.ALE)
                .upc(10L)
                .build();
    }

    @Override
    public BeerDto2 create(BeerDto2 beerDto2) {
        return BeerDto2.builder().id(UUID.randomUUID())
                .name(beerDto2.getName())
                .style(beerDto2.getStyle())
                .upc(beerDto2.getUpc())
                .build();
    }

    @Override
    public BeerDto2 update(UUID id, BeerDto2 beerDto2) {
        return BeerDto2.builder().id(id)
                .name(beerDto2.getName())
                .style(beerDto2.getStyle())
                .upc(beerDto2.getUpc())
                .build();
    }

    @Override
    public void delete(UUID id) {
        log.debug(String.format("Deleting beer with id %s", id));
    }
}