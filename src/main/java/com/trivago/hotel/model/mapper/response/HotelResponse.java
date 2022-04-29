package com.trivago.hotel.model.mapper.response;

import com.trivago.config.integration.model.Hotel;
import com.trivago.hotel.model.HotelEntity;

import java.util.Arrays;
import java.util.List;

public class HotelResponse {

    public static List<HotelEntity> mapperToHotelEntityResponse(Hotel[] hotels){
        return Arrays.stream(hotels).map( hotel ->
                HotelEntity.builder()
                        .coordenates(hotel.getCoordinates())
                        .address(hotel.getAddress())
                        .hotelId(hotel.getHotelId())
                        .name(hotel.getName())
                        .shortName(hotel.getShortName())
                        .coordenates(hotel.getCoordinates())
                        .build()
        ).toList();
    }
}
