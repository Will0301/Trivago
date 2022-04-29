package com.trivago.locator.model;

import com.trivago.hotel.model.HotelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocatorEntity {

    @Id
    private String locatorId;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    private List<HotelEntity> hotel;
}
