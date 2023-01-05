package com.nazjara.dto.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto2 {

    private UUID id;
    private String name;
    private BeerStyle style;
    private Long upc;
}
