package com.nazjara.service;

import com.nazjara.model.BeerDto;
import com.nazjara.model.BeerPagedList;
import com.nazjara.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID id, Boolean showInventoryOnHand);
    BeerDto getByUpc(String upc, Boolean showInventoryOnHand);
    BeerDto save(BeerDto beer);
    BeerDto update(UUID id, BeerDto beer);
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
}
