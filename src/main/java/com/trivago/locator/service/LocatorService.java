package com.trivago.locator.service;

import com.trivago.config.exception.notfound.NotFoundException;
import com.trivago.locator.model.LocatorEntity;
import com.trivago.locator.model.mapper.request.LocatorEntityRequest;
import com.trivago.locator.model.mapper.request.LocatorRequest;
import com.trivago.locator.repository.LocatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocatorService {

    private final LocatorRepository locatorRepository;

    public LocatorEntity signIn(LocatorEntityRequest locator){
        if (locatorRepository
                .findAllByCpf(locator.getCpf())
                .isEmpty())
            return locatorRepository.save(LocatorRequest.locattorMapperRequest(locator));
        throw new NotFoundException("");
    }

    public LocatorEntity findByCpf(String cpf){
        return locatorRepository.findByCpf(cpf);
    }

    public LocatorEntity rentHotel(LocatorEntity locator){
        if(locatorRepository
                .findAllByCpf(locator.getCpf())
                .isEmpty()) throw new NotFoundException("Not Found");
        else return locatorRepository.save(locator);
    }

    public LocatorEntity clearHotel(String cpf){
        if (locatorRepository
                .findAllByCpf(cpf)
                .isEmpty()) throw new NotFoundException("Not Found");

        locatorRepository
                .findByCpf(cpf)
                .setHotel(null);

        return locatorRepository
                .findByCpf(cpf);
    }

    public void deleteLocator(String cpf){
        locatorRepository.deleteByCpf(cpf);
    }
}
