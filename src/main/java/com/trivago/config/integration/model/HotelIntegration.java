package com.trivago.config.integration.model;

import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Builder
public class HotelIntegration {

    private final RestTemplate restTemplate;

    public Hotel[] search(String city){

        return restTemplate.getForObject("/search?q=" + city, Hotel[].class);

    }
}
