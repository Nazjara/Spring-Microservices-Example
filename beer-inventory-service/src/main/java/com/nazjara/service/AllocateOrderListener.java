package com.nazjara.service;

import com.nazjara.config.JmsConfig;
import com.nazjara.model.event.AllocateOrderRequest;
import com.nazjara.model.event.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AllocateOrderListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request) {
        log.debug("Got allocate order request: " + request.getBeerOrder());

        var resultBuilder = AllocateOrderResult.builder()
                .beerOrder(request.getBeerOrder());

        try {
            var allocated = allocationService.allocateOrder(request.getBeerOrder());
            resultBuilder.pendingInventory(!allocated);
        } catch (Exception e) {
            resultBuilder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESULT_QUEUE, resultBuilder.build());
    }
}
