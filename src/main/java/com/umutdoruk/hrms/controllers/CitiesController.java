package com.umutdoruk.hrms.controllers;

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
    public HttpStatus create(@RequestBody City city) {
        cityService.create(city);
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
    public ResponseEntity<List<City>> getAll(){
        List<City> cityList = cityService.getAll();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@PathVariable("id") Long id){
         City city = cityService.getById(id);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }
}
