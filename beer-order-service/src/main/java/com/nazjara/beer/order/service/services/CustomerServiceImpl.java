package com.nazjara.beer.order.service.services;

import com.nazjara.beer.order.service.model.CustomerPagedList;
import com.nazjara.beer.order.service.repositories.CustomerRepository;
import com.nazjara.beer.order.service.web.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerPagedList listCustomers(Pageable pageable) {
        var customerPage = customerRepository.findAll(pageable);

        return new CustomerPagedList(customerPage.stream()
                .map(customerMapper::customerToDto)
                .collect(Collectors.toList()),
                PageRequest.of(customerPage.getPageable().getPageNumber(),
                        customerPage.getPageable().getPageSize()),
                customerPage.getTotalElements());
    }
}
