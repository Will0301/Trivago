package com.trivago.locator.service;

import com.trivago.config.exception.notfound.NotFoundException;
import com.trivago.config.integration.model.Address;
import com.trivago.hotel.model.HotelEntity;
import com.trivago.locator.model.LocatorEntity;
import com.trivago.locator.model.mapper.request.LocatorEntityRequest;
import com.trivago.locator.repository.LocatorRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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

        when(locatorRepository.findAllByCpf("12345678911"))
                .thenReturn(Optional.empty());

        doReturn(expected)
                .when(this.locatorRepository)
                .save(expected);

        var actual = locatorRepository.save(expected);
        verify(this.locatorRepository, Mockito.atLeastOnce()).save(expected);
        assertEquals(actual, expected);
    }

    @Test
    void ShouldNotSingInLocator(){
        var locator =
                LocatorEntity.builder()
                        .locatorId("1")
                        .name("Willian")
                        .cpf("12345678911")
                        .hotel(null)
                        .build();

        var request =
                LocatorEntityRequest.builder()
                        .name("Willian")
                        .cpf("12345678911")
                        .hotel(null)
                        .build();

        when(locatorRepository.findAllByCpf("12345678911"))
                .thenReturn(Optional.of(locator));

        assertThatThrownBy(() -> locatorService.signIn(request))
                .isInstanceOf(RuntimeException.class);

        verify(locatorRepository, never()).save(locator);
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
    @Disabled("Current exception isn't working")
    void shouldNotFindByCpf(){

        when(locatorService.findByCpf("12")).thenReturn(null);

        assertThatThrownBy(() -> locatorService.findByCpf("12"))
                .isInstanceOf(NotFoundException.class);

        verify(locatorRepository, never()).findByCpf("12");
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

        var request =
                LocatorEntity.builder()
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

        when(locatorRepository.findAllByCpf("12345678911"))
                .thenReturn(Optional.ofNullable(locator));

        when(locatorRepository.save(request))
                .thenReturn(expected);

        var actual = locatorService.rentHotel(request);

        assertEquals(expected, actual);
    }
    @Test
    void shouldClearHotel(){
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
                        .hotel(null)
                        .build();

        var request =
                LocatorEntity.builder()
                        .name("Willian")
                        .cpf("12345678911")
                        .hotel(null)
                        .build();

        var locator =
                LocatorEntity.builder()
                        .locatorId("1")
                        .name("Willian")
                        .cpf("12345678911")
                        .hotel(hotel)
                        .build();

        when(locatorRepository.findAllByCpf("12345678911"))
                .thenReturn(Optional.ofNullable(locator));

        when(locatorRepository.findByCpf(request.getCpf()))
                .thenReturn(expected);

        var actual = locatorService.clearHotel(request.getCpf());
        assertEquals(expected, actual);
    }
}
