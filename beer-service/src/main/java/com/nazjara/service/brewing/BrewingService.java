package com.nazjara.service.brewing;

import com.nazjara.config.JmsConfig;
import com.nazjara.model.event.BrewBeerEvent;
import com.nazjara.mapper.BeerMapper;
import com.nazjara.model.Beer;
import com.nazjara.repository.BeerRepository;
import com.nazjara.service.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        var beers = beerRepository.findAll();

        beers.forEach(beer -> {
            var onHandInventory = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("Min on hand is " + beer.getMinOnHand());
            log.debug("Inventory is " + onHandInventory);

            if (beer.getMinOnHand() >= onHandInventory) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}