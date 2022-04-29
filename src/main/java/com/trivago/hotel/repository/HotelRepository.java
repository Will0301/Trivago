package com.trivago.hotel.repository;

import com.trivago.hotel.model.HotelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<HotelEntity, String> {

    List<HotelEntity> findAllByAddressCityContainingIgnoreCase(String city);

    List<HotelEntity> findHotelEntityByHotelId(String hotelId);
}
