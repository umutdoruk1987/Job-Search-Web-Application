package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.City;

import java.util.List;

public interface CityService {

    void create(City city);
    void update(String cityName);
    void delete(Long id);
    List<City> getAll();
    City getById(Long id);
}
