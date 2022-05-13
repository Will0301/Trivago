package com.trivago.locator.model.mapper.request;

import com.trivago.hotel.model.HotelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocatorEntityRequest {

    @NotNull
    //@NotBlank
    private String name;

    @NotNull
    @Size(min = 11, max = 12)
    private String cpf;

    private HotelEntity hotel;
    }

