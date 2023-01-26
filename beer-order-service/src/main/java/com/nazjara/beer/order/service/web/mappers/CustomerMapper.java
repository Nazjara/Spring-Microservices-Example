package com.nazjara.beer.order.service.web.mappers;

import com.nazjara.beer.order.service.domain.Customer;
import com.nazjara.beer.order.service.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);
    Customer dtoToCustomer(Customer dto);
}
