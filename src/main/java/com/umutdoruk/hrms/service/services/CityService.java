package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.CityRequest;
import com.umutdoruk.hrms.DTO.response.CityResponse;
import com.umutdoruk.hrms.entities.City;

import java.util.List;

public interface CityService {

    void create(CityRequest cityRequest);
    void update(String cityName);
    void delete(Long id);
    List<CityResponse> getAll();
    City getById(Long id);
}
