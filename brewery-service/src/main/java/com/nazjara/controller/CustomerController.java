package com.nazjara.controller;

import com.nazjara.dto.CustomerDto;
import com.nazjara.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable UUID id) {
        return new ResponseEntity<>(customerService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CustomerDto customerDto) {
        var headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + customerService.create(customerDto).getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable UUID id, @RequestBody @Valid CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.update(id, customerDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        customerService.delete(id);
    }
}