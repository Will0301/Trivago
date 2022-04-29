package com.trivago.hotel.controller;

import com.trivago.config.integration.model.Address;
import com.trivago.hotel.facade.HotelFacade;
import com.trivago.hotel.model.HotelEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = Application.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelFacade hotelFacade;

    @Test
    void shouldReturnHotelsList() throws Exception {
        List<HotelEntity> result =
                List.of(HotelEntity.builder()
                                .hotelId("1")
                                .address(Address.builder()
                                        .city("Paris")
                                        .country("France")
                                        .address("Somewhere at 1555")
                                        .build())
                                .coordenates("-15,5554, -17.885")
                                .name("Paris 5 stars")
                                .shortName("Paris 5")
                        .build());

        Mockito.when(hotelFacade.search("paris")).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/search/paris"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnHotelsList4xx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/searc/paris"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldListAllHotels() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/hotels"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldListAllHotels4xx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/hotel"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldFindHotelById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/hotels?hotelId=123"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindHotelById4xx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/hotels/1112"))
                .andExpect(status().is4xxClientError());
    }
}
