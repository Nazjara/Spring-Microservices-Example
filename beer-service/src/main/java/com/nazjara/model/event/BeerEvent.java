package com.nazjara.model.event;

import com.nazjara.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -8421237022738818561L;

    private BeerDto beerDto;
}