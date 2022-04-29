package com.trivago.config.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String name;

    private String shortName;

    private Address address;

    @JsonProperty("coordenates")
    private String coordinates;

    private String hotelId;
}
