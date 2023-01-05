package com.nazjara.service.v2;

import com.nazjara.dto.v2.BeerDto2;

import java.util.UUID;

public interface BeerService2 {
    BeerDto2 get(UUID id);
    BeerDto2 create(BeerDto2 beerDto);
    BeerDto2 update(UUID id, BeerDto2 beerDto);
    void delete(UUID id);
}