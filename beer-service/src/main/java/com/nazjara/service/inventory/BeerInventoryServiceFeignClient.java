package com.nazjara.service.inventory;

import com.nazjara.config.FeignClientConfig;
import com.nazjara.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

import static com.nazjara.service.inventory.BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH;

@FeignClient(name = "beer-inventory-service", configuration = FeignClientConfig.class)
public interface BeerInventoryServiceFeignClient {

    @GetMapping(INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
