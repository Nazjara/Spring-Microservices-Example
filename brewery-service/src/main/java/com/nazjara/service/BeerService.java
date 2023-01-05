package com.nazjara.service;

import com.nazjara.dto.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto get(UUID id);
    BeerDto create(BeerDto beerDto);
    BeerDto update(UUID id, BeerDto beerDto);
    void delete(UUID id);
}