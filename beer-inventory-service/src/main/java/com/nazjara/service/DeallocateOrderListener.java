package com.nazjara.service;

import com.nazjara.config.JmsConfig;
import com.nazjara.model.event.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeallocateOrderListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request) {
        log.debug("Got deallocate order request: " + request.getBeerOrder());
        allocationService.deallocateOrder(request.getBeerOrder());
    }
}
