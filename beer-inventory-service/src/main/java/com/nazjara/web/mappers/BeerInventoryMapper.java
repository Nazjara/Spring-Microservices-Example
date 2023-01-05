package com.nazjara.web.mappers;

import com.nazjara.domain.BeerInventory;
import com.nazjara.model.BeerInventoryDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {
    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);
    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
