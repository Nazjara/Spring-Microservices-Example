package com.nazjara.beer.order.service.statemachine;

import com.nazjara.beer.order.service.domain.BeerOrderEventEnum;
import com.nazjara.beer.order.service.domain.BeerOrderStatusEnum;
import java.util.EnumSet;

import com.nazjara.beer.order.service.statemachine.actions.AllocateOrderAction;
import com.nazjara.beer.order.service.statemachine.actions.DeallocateOrderAction;
import com.nazjara.beer.order.service.statemachine.actions.ValidateOrderAction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableStateMachineFactory
public class BeerOrderStateMachineConfig extends StateMachineConfigurerAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final ValidateOrderAction validateOrderAction;
    private final AllocateOrderAction allocateOrderAction;
    private final ValidateOrderAction validationFailureAction;
    private final AllocateOrderAction allocationFailureAction;
    private final DeallocateOrderAction deallocateOrderAction;

    @Override
    public void configure(StateMachineStateConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> states) throws Exception {
        states.withStates()
                .initial(BeerOrderStatusEnum.NEW)
                .states(EnumSet.allOf(BeerOrderStatusEnum.class))
                .end(BeerOrderStatusEnum.PICKED_UP)
                .end(BeerOrderStatusEnum.DELIVERED)
                .end(BeerOrderStatusEnum.CANCELLED)
                .end(BeerOrderStatusEnum.DELIVERY_EXCEPTION)
                .end(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
                .end(BeerOrderStatusEnum.ALLOCATION_EXCEPTION);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> transitions) throws Exception {
        transitions
                .withExternal().source(BeerOrderStatusEnum.NEW).target(BeerOrderStatusEnum.VALIDATION_PENDING).event(BeerOrderEventEnum.VALIDATE_ORDER).action(validateOrderAction)
                .and()
                .withExternal().source(BeerOrderStatusEnum.VALIDATION_PENDING).target(BeerOrderStatusEnum.VALIDATED).event(BeerOrderEventEnum.VALIDATION_PASSED)
                .and()
                .withExternal().source(BeerOrderStatusEnum.VALIDATION_PENDING).target(BeerOrderStatusEnum.CANCELLED).event(BeerOrderEventEnum.CANCEL_ORDER)
                .and()
                .withExternal().source(BeerOrderStatusEnum.VALIDATION_PENDING).target(BeerOrderStatusEnum.VALIDATION_EXCEPTION).event(BeerOrderEventEnum.VALIDATION_FAILED)
                .and()
                .withExternal().source(BeerOrderStatusEnum.VALIDATED).target(BeerOrderStatusEnum.CANCELLED).event(BeerOrderEventEnum.CANCEL_ORDER)
                .and()
                .withExternal().source(BeerOrderStatusEnum.VALIDATED).target(BeerOrderStatusEnum.ALLOCATION_PENDING).event(BeerOrderEventEnum.ALLOCATE_ORDER).action(allocateOrderAction)
                .and()
                .withExternal().source(BeerOrderStatusEnum.ALLOCATION_PENDING).target(BeerOrderStatusEnum.ALLOCATED).event(BeerOrderEventEnum.ALLOCATION_SUCCESS)
                .and()
                .withExternal().source(BeerOrderStatusEnum.ALLOCATION_PENDING).target(BeerOrderStatusEnum.ALLOCATION_EXCEPTION).event(BeerOrderEventEnum.ALLOCATION_FAILED)
                .and()
                .withExternal().source(BeerOrderStatusEnum.ALLOCATION_PENDING).target(BeerOrderStatusEnum.CANCELLED).event(BeerOrderEventEnum.CANCEL_ORDER)
                .and()
                .withExternal().source(BeerOrderStatusEnum.ALLOCATION_PENDING).target(BeerOrderStatusEnum.PENDING_INVENTORY).event(BeerOrderEventEnum.ALLOCATION_NO_INVENTORY)
                .and().withExternal().source(BeerOrderStatusEnum.ALLOCATED).target(BeerOrderStatusEnum.PICKED_UP).event(BeerOrderEventEnum.BEER_ORDER_PICKED_UP)
                .and()
                .withExternal().source(BeerOrderStatusEnum.ALLOCATED).target(BeerOrderStatusEnum.CANCELLED).event(BeerOrderEventEnum.CANCEL_ORDER).action(deallocateOrderAction);
    }
}
