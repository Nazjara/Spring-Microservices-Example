package com.nazjara.controller.v2;

import com.nazjara.dto.v2.BeerDto2;
import com.nazjara.service.v2.BeerService2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/beer")
@Validated
public class BeerController2 {

    private final BeerService2 beerService;

    public BeerController2(BeerService2 beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto2> get(@PathVariable @NotNull UUID id) {
        return new ResponseEntity<>(beerService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid @NotNull BeerDto2 beerDto2) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + beerService.create(beerDto2).getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeerDto2> update(@PathVariable UUID id, @Valid @RequestBody BeerDto2 beerDto2) {
        return new ResponseEntity<>(beerService.update(id, beerDto2), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        beerService.delete(id);
    }
}