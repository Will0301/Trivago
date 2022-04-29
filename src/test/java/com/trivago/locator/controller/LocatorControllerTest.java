//package com.trivago.locator.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.trivago.config.integration.model.Address;
//import com.trivago.hotel.facade.HotelFacade;
//import com.trivago.hotel.model.HotelEntity;
//import com.trivago.hotel.service.HotelService;
//import com.trivago.locator.facade.LocatorFacade;
//import com.trivago.locator.model.LocatorEntity;
//import com.trivago.locator.service.LocatorService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@WebAppConfiguration
//@AutoConfigureMockMvc
////@WebMvcTest(controllers = LocatorController.class)
//@ContextConfiguration(
//        classes = {LocatorController.class, LocatorFacade.class, LocatorService.class, HotelFacade.class,
//                HotelService.class}
//)
////@RunWith(SpringRunner.class)
//class LocatorControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private LocatorService locatorService;
//
//    @Mock
//    private HotelService hotelService;
//
//    @MockBean
//    private LocatorFacade locatorFacade;
//
//    @MockBean
//    private HotelFacade hotelFacade;
//
//    @InjectMocks
//    private LocatorController locatorController;
//
//    @Test
//    void shouldSaveLocator() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/trivago/sign"))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @Disabled
//    void shouldRentHotelStatusOk() throws Exception {
//
//        HotelEntity hotel = HotelEntity.builder()
//                .hotelId("1")
//                .address(Address.builder()
//                        .city("Paris")
//                        .country("France")
//                        .address("Somewhere at 1555")
//                        .build())
//                .coordenates("-15,5554, -17.885")
//                .name("Paris 5 stars")
//                .shortName("Paris 5")
//                .build();
//
//        LocatorEntity request = LocatorEntity.builder()
//                .cpf("123")
//                .name("Willian")
//                .locatorId("1")
//                .hotel(null)
//                .build();
//
//        LocatorEntity response = LocatorEntity.builder()
//                .cpf("123")
//                .name("Willian")
//                .locatorId("1")
//                .hotel(hotel)
//                .build();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        var response1 = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/trivago/rent")
//                        .contentType(objectMapper.writeValueAsString(locatorController.rentHotel(request.getCpf(), hotel.getHotelId())))
//                            .contentType(objectMapper.writeValueAsString(locatorFacade.rentHotel(request.getCpf(), hotel.getHotelId())))
//                        .contentType(objectMapper.writeValueAsString(hotelFacade.findById("1")))
//                        .content(objectMapper.writeValueAsString(APPLICATION_JSON)))
//                .andExpect(status().isOk());
//
//        /*Mockito.doReturn(response).
//                when(locatorFacade.rentHotel(request.getCpf(), hotel.getHotelId()));*/
//
//        Mockito.doReturn(response)
//                .when(locatorService.rentHotel(request));
//        //request.getCpf(), hotel.getHotelId()
//
//        Mockito.doReturn(hotel).
//                when(hotelService.findById("1"));
//
////        Mockito.verify(this.locatorFacade, Mockito.atLeastOnce()).rentHotel(request.getCpf(), hotel.getHotelId());
//
//        var actual = new ObjectMapper()
//                .readValue(response1.andReturn().getResponse().getContentAsString(), LocatorEntity.class);
//
//        Assertions.assertEquals(response, actual);
//
//    }
//
//    @Test
//    void deleteLocator() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/trivago/signout?cpf=222"))
//                .andExpect(status().isNoContent());
//    }
//}
