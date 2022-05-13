package com.trivago.locator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.trivago.hotel.controller.HotelController;
import com.trivago.hotel.model.HotelEntity;
import com.trivago.integration.model.Address;
import com.trivago.locator.facade.LocatorFacade;
import com.trivago.locator.model.LocatorEntity;
import com.trivago.locator.model.mapper.request.LocatorEntityRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureDataMongo
@ExtendWith(SpringExtension.class)
@WebMvcTest(LocatorController.class)
class LocatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocatorFacade locatorFacade;

    @Test
    @Disabled("415")
    void shouldSaveLocator() throws Exception {

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

        var locatorRequest = LocatorEntityRequest.builder()
                .cpf("12345678911")
                .name("Will")
                .build();

        var locatorResponse = LocatorEntity.builder()
                .locatorId("1")
                .cpf("12345678911")
                .name("Will")
                .build();

        Mockito.when(locatorFacade.signIn(locatorRequest))
                .thenReturn(locatorResponse);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonResponse= objectWriter.writeValueAsString(locatorResponse);
        String jsonRequest= objectWriter.writeValueAsString(locatorRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/trivago/sign")
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldRentHotelStatusOk() throws Exception {

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

        var locatorRequest = LocatorEntityRequest.builder()
                .cpf("12345678911")
                .name("Will")
                .build();

        var locatorResponse = LocatorEntity.builder()
                .locatorId("1")
                .cpf("12345678911")
                .name("Will")
                .hotel(hotel)
                .build();

        Mockito.when(locatorFacade.rentHotel(locatorRequest.getCpf(), hotel.get(0).getHotelId()))
                .thenReturn(locatorResponse);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String jsonResponse= objectWriter.writeValueAsString(locatorResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/trivago/rent?cpf=12345678911&hotelId=1"))
                .andExpect(content().json(jsonResponse))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLocator() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/trivago/signout?cpf=222"))
                .andExpect(status().isNoContent());
    }
}
