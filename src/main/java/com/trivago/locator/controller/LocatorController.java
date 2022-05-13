package com.trivago.locator.controller;

import com.trivago.locator.facade.LocatorFacade;
import com.trivago.locator.model.LocatorEntity;
import com.trivago.locator.model.mapper.request.LocatorEntityRequest;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/trivago")
@AllArgsConstructor
public class LocatorController {

    private final LocatorFacade locatorFacade;

    @PostMapping("/sign")
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Register a locator")
    public LocatorEntity signIn(@RequestBody LocatorEntityRequest locator){
        return locatorFacade.signIn(locator);
    }

    @GetMapping("/rent")
    @ApiOperation(value = "Rent a hotel")
    public LocatorEntity rentHotel(@RequestParam String cpf, @RequestParam String hotelId){
        return locatorFacade.rentHotel(cpf, hotelId);
    }

    @GetMapping("/test")
    public LocatorEntity test(@RequestParam String cpf){
        return locatorFacade.findByCpf(cpf);
    }

    @PutMapping("/clear")
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Clear the hotel in a locator")
        public LocatorEntity clearHotel(@RequestParam String cpf){
        return locatorFacade.clearHotel(cpf);
    }

    @DeleteMapping("/signout")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Delete locator")
    public void deleteLocator(@RequestParam String cpf){
        locatorFacade.deleteLocator(cpf);
    }
}
