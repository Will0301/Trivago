package com.trivago.locator.facade;

import com.trivago.hotel.facade.HotelFacade;
import com.trivago.locator.model.LocatorEntity;
import com.trivago.locator.model.mapper.request.LocatorEntityRequest;
import com.trivago.locator.service.LocatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LocatorFacade {

    private final LocatorService locatorService;

    private final HotelFacade hotelFacade;

    public LocatorEntity rentHotel(String cpf, String hotelId){
        LocatorEntity locator = locatorService.findByCpf(cpf);
        locator.setHotel(hotelFacade.findById(hotelId));
        return locator;
    }

    public LocatorEntity signIn(LocatorEntityRequest locator){
        return locatorService.signIn(locator);
    }

    public LocatorEntity findByCpf(String cpf){
        return locatorService.findByCpf(cpf);
    }

    public LocatorEntity clearHotel(String cpf){
        return locatorService.clearHotel(cpf);
    }

    public void deleteLocator(String cpf){
        locatorService.deleteLocator(cpf);
    }
}
