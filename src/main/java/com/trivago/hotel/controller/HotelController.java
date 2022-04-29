package com.trivago.hotel.controller;

import com.trivago.hotel.facade.HotelFacade;
import com.trivago.hotel.model.HotelEntity;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trivago")
@AllArgsConstructor
public class HotelController {

    private final HotelFacade hotelFacade;
    
    @GetMapping("/search/{city}")
    @ApiOperation(value = "Search for a hotel in a city")
    public List<HotelEntity> search(@PathVariable String city){
        return hotelFacade.search(city);
    }

    @GetMapping("/hotels")
    @ApiOperation(value = "List all the hotels saved")
    public List<HotelEntity> listHotels(){
        return hotelFacade.listHotels();
    }

    @GetMapping("/hotel")
    @ApiOperation(value = "Search a hotel by id")
    public List<HotelEntity> findById(@RequestParam String hotelId){
        return hotelFacade.findById(hotelId);
    }
}
