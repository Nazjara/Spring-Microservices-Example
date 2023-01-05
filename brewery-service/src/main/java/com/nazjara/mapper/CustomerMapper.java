package com.nazjara.mapper;

import com.nazjara.dto.CustomerDto;
import com.nazjara.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}