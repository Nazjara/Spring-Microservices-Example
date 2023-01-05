package com.nazjara.service;

import com.nazjara.dto.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto get(UUID id) {
        return BeerDto.builder().id(UUID.randomUUID())
                .name("Galaxy Cat")
                .style("Pale Ale")
                .upc(10L)
                .build();
    }

    @Override
    public BeerDto create(BeerDto beerDto) {
        return BeerDto.builder().id(UUID.randomUUID())
                .name(beerDto.getName())
                .style(beerDto.getStyle())
                .upc(beerDto.getUpc())
                .build();
    }

    @Override
    public BeerDto update(UUID id, BeerDto beerDto) {
        return BeerDto.builder().id(id)
                .name(beerDto.getName())
                .style(beerDto.getStyle())
                .upc(beerDto.getUpc())
                .build();
    }

    @Override
    public void delete(UUID id) {
        log.debug(String.format("Deleting beer with id %s", id));
    }
}