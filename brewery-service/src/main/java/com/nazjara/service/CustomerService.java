package com.nazjara.service;

import com.nazjara.dto.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto get(UUID id);
    CustomerDto create(CustomerDto customerDto);
    CustomerDto update(UUID id, CustomerDto customerDto);
    void delete(UUID id);
}