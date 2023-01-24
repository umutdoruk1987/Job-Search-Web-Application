package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.CityRequest;
import com.umutdoruk.hrms.DTO.response.CityResponse;
import com.umutdoruk.hrms.entities.City;
import com.umutdoruk.hrms.service.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public HttpStatus create(@RequestBody CityRequest cityRequest) {
        cityService.create(cityRequest);
        return HttpStatus.CREATED;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        cityService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{cityName}")
    public HttpStatus update(@PathVariable("cityName") String cityName) {
        cityService.update(cityName);
        return HttpStatus.OK;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CityResponse>> getAll(){
        List<CityResponse> cityList = cityService.getAll();
        return new ResponseEntity<CityResponse>(cityList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getById(@PathVariable("id") Long id){
         CityResponse cityResponse = cityService.getById(id);
        return new ResponseEntity<>(cityResponse,HttpStatus.OK);
    }
}
