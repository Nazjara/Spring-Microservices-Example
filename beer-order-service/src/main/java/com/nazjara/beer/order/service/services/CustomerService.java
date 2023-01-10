package com.nazjara.beer.order.service.services;

import com.nazjara.beer.order.service.model.CustomerPagedList;

import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerPagedList listCustomers(Pageable pageable);
}
