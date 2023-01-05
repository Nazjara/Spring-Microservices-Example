package com.nazjara.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;
    private Timestamp createdDate;
    private Timestamp lastUpdatedDate;
}
