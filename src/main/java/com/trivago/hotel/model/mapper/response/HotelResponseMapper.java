package com.trivago.hotel.model.mapper.response;

import java.util.Arrays;
import java.util.List;

import com.trivago.hotel.model.HotelEntity;
import com.trivago.hotel.model.HotelResponse;
import com.trivago.integration.model.Hotel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HotelResponseMapper {
    
    public static List<HotelEntity> mapperToHotelEntityResponse(Hotel[] hotels) {
        return Arrays.stream(hotels).map(hotel ->
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
    
    
    public static HotelResponse mapperToHotelResponse(HotelEntity hotelsEntity) {
        return HotelResponse.builder()
            .coordenates(hotelsEntity.getCoordenates())
            .address(hotelsEntity.getAddress())
            .hotelId(hotelsEntity.getHotelId())
            .name(hotelsEntity.getName())
            .shortName(hotelsEntity.getShortName())
            .build();
    }
}
