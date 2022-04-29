//package com.trivago.locator.controller;
//
//import com.trivago.hotel.facade.HotelFacade;
//import com.trivago.hotel.service.HotelService;
//import com.trivago.locator.facade.LocatorFacade;
//import com.trivago.locator.service.LocatorService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
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
//    void shouldRentHotelStatusOk() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("http://localhost:8080/trivago/rent?cpf=12345678911&hotelId=12"))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void deleteLocator() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/trivago/signout?cpf=222"))
//                .andExpect(status().isNoContent());
//    }
//}
