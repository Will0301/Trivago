package com.trivago.locator.model.mapper.response;

import com.trivago.hotel.model.HotelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocatorEntityResponse {

    private String name;

    private HotelEntity hotel;
}
