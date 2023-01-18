package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.concretes.City;

import java.util.List;

public interface CityService {
    List<City> getAll();
    City getById(int id);
}
