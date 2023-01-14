package com.nazjara.service.inventory;

import com.nazjara.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeerInventoryServiceFeign implements BeerInventoryService {

    private final BeerInventoryServiceFeignClient feignClient;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling Inventory Service");

        var responseEntity = feignClient.getOnHandInventory(beerId);

        //sum from inventory list
        return Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }
}
