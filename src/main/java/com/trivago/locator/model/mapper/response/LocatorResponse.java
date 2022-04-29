package com.trivago.locator.model.mapper.response;

import com.trivago.locator.model.LocatorEntity;


public class LocatorResponse {

    public static LocatorEntityResponse mapperTolocatorResponse(LocatorEntity locator){
        return LocatorEntityResponse.builder()
                .name(locator.getName())
                .hotel(null)
                .build();
    }
}
