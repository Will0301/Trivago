package com.trivago.integration;

import com.trivago.integration.model.Hotel;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Builder
public class HotelIntegration {

    private final RestTemplate restTemplate;

    public Hotel[] search(String city){

        return restTemplate.getForObject("/search?q=" + city, Hotel[].class);
    }
}
