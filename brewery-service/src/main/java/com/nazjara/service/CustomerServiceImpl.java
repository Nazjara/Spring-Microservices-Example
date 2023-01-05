package com.nazjara.service;

import com.nazjara.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto get(UUID id) {
        return CustomerDto.builder().id(UUID.randomUUID())
                .name("Customer's name")
                .build();
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        return CustomerDto.builder().id(UUID.randomUUID())
                .name(customerDto.getName())
                .build();
    }

    @Override
    public CustomerDto update(UUID id, CustomerDto customerDto) {
        return CustomerDto.builder().id(id)
                .name(customerDto.getName())
                .build();
    }

    @Override
    public void delete(UUID id) {
        log.debug(String.format("Deleting customer with id %s", id));
    }
}