package com.trivago.locator.service;

import com.trivago.config.integration.model.Address;
import com.trivago.hotel.model.HotelEntity;
import com.trivago.locator.model.LocatorEntity;
import com.trivago.locator.model.mapper.request.LocatorEntityRequest;
import com.trivago.locator.repository.LocatorRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocatorServiceTest {

    @InjectMocks
    LocatorService locatorService;

    @Mock
    LocatorRepository locatorRepository;

    @Test
    void ShouldSingInLocator(){
        var expected =
                LocatorEntity.builder()
                .locatorId("1")
                .name("Willian")
                .cpf("12345678911")
                .hotel(null)
                .build();
        var locator =
                LocatorEntityRequest.builder()
                        .name("Willian")
                        .cpf("12345678911")
                        .hotel(null)
                        .build();

        doReturn(expected)
                .when(this.locatorRepository)
                .save(expected);
        var actual = locatorRepository.save(expected);
        verify(this.locatorRepository, Mockito.atLeastOnce()).save(expected);
        assertEquals(actual, expected);
    }

    @Test
    void shouldFindByCpf(){
        var expected =
                LocatorEntity.builder()
                        .locatorId("1")
                        .name("Willian")
                        .cpf("12345678911")
                        .hotel(null)
                        .build();

        doReturn(expected)
                .when(this.locatorRepository)
                .findByCpf("12345678911");
        var actual = locatorService.findByCpf("12345678911");
        verify(this.locatorRepository, atLeastOnce()).findByCpf("12345678911");
        assertEquals(actual, expected);
    }

    @Test
    void ShouldRentHotel(){
        var hotel =
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

        var expected =
                LocatorEntity.builder()
                        .locatorId("1")
                        .name("Willian")
                        .cpf("12345678911")
                        .hotel(hotel)
                        .build();
        var locator =
                LocatorEntity.builder()
                        .locatorId("1")
                        .name("Willian")
                        .cpf("12345678911")
                        .hotel(null)
                        .build();

        doReturn(false)
                .when(this.locatorRepository)
                .findAllByCpf("12345678911");
        doReturn(expected)
                .when(this.locatorRepository)
                .save(locator);
        var actual = locatorService.rentHotel(locator);
        assertEquals(expected, actual);
        verify(this.locatorRepository, atLeastOnce())
                .save(expected);
    }
}
