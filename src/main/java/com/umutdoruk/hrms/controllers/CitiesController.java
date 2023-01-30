package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.CityRequest;
import com.umutdoruk.hrms.DTO.response.CityResponse;
import com.umutdoruk.hrms.service.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }
    // ROLE_EMPLOYER
    @PostMapping("/create")
    public HttpStatus create(@RequestBody CityRequest cityRequest) {
        cityService.create(cityRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/{cityName}")
    public HttpStatus update(@RequestBody CityRequest cityRequest) {
        cityService.update(cityRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        cityService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityResponseById (@PathVariable("id") Long id){
         CityResponse cityResponse = cityService.getCityResponseById(id);
        return new ResponseEntity<>(cityResponse,HttpStatus.OK);
    }
}
