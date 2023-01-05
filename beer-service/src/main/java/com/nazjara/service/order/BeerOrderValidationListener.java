package com.nazjara.service.order;

import com.nazjara.config.JmsConfig;
import com.nazjara.model.event.ValidateOrderRequest;
import com.nazjara.model.event.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator validator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest request) {
        var isValid = validator.validateOrder(request.getBeerOrder());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
            .isValid(isValid)
            .orderId(request.getBeerOrder().getId())
            .build());
    }
}
