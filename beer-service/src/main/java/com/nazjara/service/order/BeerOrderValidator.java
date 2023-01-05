package com.nazjara.service.order;

import com.nazjara.model.BeerOrderDto;
import com.nazjara.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrder) {
        var beersNotFound =  beerOrder.getBeerOrderLines().stream()
                .filter(orderLine -> beerRepository.findByUpc(orderLine.getUpc()) == null)
                .collect(Collectors.toList());

        return beersNotFound.size() == 0;
    }
}
