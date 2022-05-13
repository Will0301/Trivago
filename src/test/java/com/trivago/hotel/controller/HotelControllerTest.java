package com.trivago.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.trivago.hotel.facade.HotelFacade;
import com.trivago.hotel.model.HotelEntity;
import com.trivago.hotel.model.HotelResponse;
import com.trivago.hotel.model.mapper.response.HotelResponseMapper;
import com.trivago.hotel.repository.HotelRepository;
import com.trivago.integration.model.Address;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.trivago.hotel.model.mapper.response.HotelResponseMapper.mapperToHotelResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureDataMongo
@EnableMongoRepositories("com.trivago.hotel.repository.HotelRepository")
@ExtendWith(SpringExtension.class)
@WebMvcTest(HotelController.class)
class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelFacade hotelFacade;

    public String URL = "http://localhost:8080/trivago";
    @Test
    void shouldReturnHotelsList() throws Exception {

        var hotel = List.of( HotelResponse.builder()
                .hotelId("1")
                .address(Address.builder()
                        .city("Paris")
                        .country("France")
                        .street("Somewhere at 1555")
                        .build())
                .name("Paris Five Stars")
                .shortName("P5S")
                .build());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonResponse= objectWriter.writeValueAsString(hotel);

        Mockito.when(hotelFacade.search("paris"))
                .thenReturn(hotel);


        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/search/paris"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldReturnHotelsList4xx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/searc/paris"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldListAllHotels() throws Exception {

        var hotel = List.of( HotelEntity.builder()
                .hotelId("1")
                .address(Address.builder()
                        .city("Paris")
                        .country("France")
                        .street("Somewhere at 1555")
                        .build())
                .name("Paris Five Stars")
                .shortName("P5S")
                .build());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonResponse= objectWriter.writeValueAsString(hotel);

        Mockito.when(hotelFacade.listHotels())
                .thenReturn(hotel);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/hotels"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldListAllHotels4xx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/hotel"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFindHotelById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/hotels?hotelId=123"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindHotelById4xx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/hotels/1112"))
                .andExpect(status().isNotFound());
    }
}
