package com.trivago.hotel.facade;

import java.util.List;

import com.trivago.hotel.model.HotelEntity;
import com.trivago.hotel.model.HotelResponse;
import com.trivago.hotel.model.mapper.response.HotelResponseMapper;
import com.trivago.hotel.service.HotelService;
import com.trivago.integration.HotelIntegration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HotelFacade {
    
    private final HotelService hotelService;
    
    private final HotelIntegration hotelIntegration;
    
    public List<HotelResponse> search(String city) {
        List<HotelEntity> hotels = hotelService.searchByCity(city);
        
        if (hotels.isEmpty()) {
            return hotelService.saveAll
                    (HotelResponseMapper.mapperToHotelEntityResponse(hotelIntegration.search(city)))
                .stream().map(HotelResponseMapper::mapperToHotelResponse)
                .toList();
        }
        return hotels.stream().map(HotelResponseMapper::mapperToHotelResponse).toList();
    }
    
    public List<HotelEntity> listHotels() {
        return hotelService.listHotels();
    }
    
    public List<HotelEntity> findById(String hotelId) {
        return hotelService.findById(hotelId);
    }
}
