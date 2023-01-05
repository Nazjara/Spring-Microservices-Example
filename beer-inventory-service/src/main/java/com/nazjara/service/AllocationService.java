package com.nazjara.service;

import com.nazjara.model.BeerOrderDto;

public interface AllocationService {
    boolean allocateOrder(BeerOrderDto beerOrderDto);
    void deallocateOrder(BeerOrderDto beerOrderDto);
}
