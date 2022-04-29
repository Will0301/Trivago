package com.trivago.config.integration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplateBuilder()
                .rootUri("https://hotel-price-aggregator.p.rapidapi.com")
                .defaultHeader("x-rapidapi-host", "hotel-price-aggregator.p.rapidapi.com")
                .defaultHeader("x-rapidapi-key", "108abdd2f4msh1b627092924ac42p1a4cf1jsn14d423951b26")
                .build();
    }
}
