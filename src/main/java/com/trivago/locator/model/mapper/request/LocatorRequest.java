package com.trivago.locator.model.mapper.request;

import com.trivago.locator.model.LocatorEntity;

public class LocatorRequest {

    public static LocatorEntity locattorMapperRequest(LocatorEntityRequest locator){
        return LocatorEntity.builder()
                .cpf(locator.getCpf())
                .name(locator.getName())
                .hotel(null)
                .build();
    }
}
