package com.trivago.hotel.service;

import com.trivago.hotel.model.HotelEntity;
import com.trivago.hotel.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<HotelEntity> searchByCity(String city){
        return hotelRepository.findAllByAddressCityContainingIgnoreCase(city);
    }

    public List<HotelEntity> saveAll(List<HotelEntity> hotels){
        return hotelRepository.saveAll(hotels);
    }

    public List<HotelEntity> listHotels(){
        return hotelRepository.findAll();
    }

    public List<HotelEntity> findById(String hotelId){
        return hotelRepository.findHotelEntityByHotelId((hotelId));
    }
}
