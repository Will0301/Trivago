package com.trivago.hotel.model;

import com.trivago.integration.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponse {
    private String name;
    private String shortName;
    private Address address;
    private String coordenates;
    private String hotelId;
}