package com.trivago.hotel.facade;

import com.trivago.config.integration.model.HotelIntegration;
import com.trivago.hotel.service.HotelService;
import com.trivago.hotel.model.mapper.response.HotelResponse;
import com.trivago.hotel.model.HotelEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HotelFacade {

    private final HotelService hotelService;

    private  final HotelIntegration hotelIntegration;
    public List<HotelEntity> search(String city) {
        List<HotelEntity> hotels = hotelService.searchByCity(city);

      var test =  Optional.of(hotels)
                .or(Optional::empty)
                .map(a -> hotelIntegration.search(city))
                .map(HotelResponse::mapperToHotelEntityResponse)
                .map(hotelService::saveAll).get();


//        if (hotels.isEmpty()) return
//                hotelService.saveAll
//                        (HotelResponse.mapperToHotelEntityResponse(hotelIntegration.search(city)));
//        return hotels;
        return test;
    }

    public List<HotelEntity> listHotels() {
        return hotelService.listHotels();
    }

    public List<HotelEntity> findById(String hotelId) {
        return hotelService.findById(hotelId);
    }
}
