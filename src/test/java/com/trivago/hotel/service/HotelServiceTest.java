package com.trivago.hotel.service;

import com.trivago.hotel.model.HotelEntity;
import com.trivago.hotel.repository.HotelRepository;
import com.trivago.integration.model.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;

//@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@AutoConfigureDataMongo
@ExtendWith(SpringExtension.class)
class HotelServiceTest {

    @InjectMocks
    HotelService hotelService;

    @Autowired
    HotelRepository hotelRepository;

    @Test
    void shouldReturnHotelByCity() {
        var expected =
                List.of(HotelEntity.builder()
                        .hotelId("1")
                        .address(Address.builder()
                                .city("Paris")
                                .country("France")
                                .street("Somewhere at 1555")
                                .build())
                        .coordenates("-15,5554, -17.885")
                        .name("Paris 5 stars")
                        .shortName("Paris 5")
                        .build());

        Mockito.doReturn(expected)
                .when(this.hotelRepository)
                .findAllByAddressCityContainingIgnoreCase("paris");

        var actual = hotelService.searchByCity("paris");

        Mockito.verify(hotelRepository, atLeastOnce()).findAllByAddressCityContainingIgnoreCase("paris");
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void shouldSaveAllHotels() {
        var expected =
                List.of(HotelEntity.builder()
                        .hotelId("1")
                        .address(Address.builder()
                                .city("Paris")
                                .country("France")
                                .street("Somewhere at 1555")
                                .build())
                        .coordenates("-15,5554, -17.885")
                        .name("Paris 5 stars")
                        .shortName("Paris 5")
                        .build());

        doReturn(expected)
                .when(this.hotelRepository)
                .saveAll(expected);

        var actual = hotelRepository.saveAll(expected);

        Mockito.verify(hotelRepository, atLeastOnce())
                .saveAll(expected);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void shouldListAllHotels() {
        var expected =
                List.of(HotelEntity.builder()
                        .hotelId("1")
                        .address(Address.builder()
                                .city("Paris")
                                .country("France")
                                .street("Somewhere at 1555")
                                .build())
                        .coordenates("-15,5554, -17.885")
                        .name("Paris 5 stars")
                        .shortName("Paris 5")
                        .build());

        doReturn(expected)
                .when(hotelRepository)
                .findAll();

        var actual = hotelRepository.findAll();
        Mockito.verify(hotelRepository, atLeastOnce())
                .findAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldFindHotelById() {
        var expected =
                List.of(HotelEntity.builder()
                        .hotelId("1")
                        .address(Address.builder()
                                .city("Paris")
                                .country("France")
                                .street("Somewhere at 1555")
                                .build())
                        .coordenates("-15,5554, -17.885")
                        .name("Paris 5 stars")
                        .shortName("Paris 5")
                        .build());

        doReturn(expected)
                .when(this.hotelRepository)
                .findHotelEntityByHotelId("1");
        var actual = hotelRepository.findHotelEntityByHotelId("1");
        Mockito.verify(this.hotelRepository, atLeastOnce())
                .findHotelEntityByHotelId("1");
        Assertions.assertEquals(actual, expected);
    }
}
