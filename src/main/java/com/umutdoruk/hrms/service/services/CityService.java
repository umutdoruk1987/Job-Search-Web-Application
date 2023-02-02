package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.CityRequest;
import com.umutdoruk.hrms.DTO.response.CityResponse;
import com.umutdoruk.hrms.entities.City;

public interface CityService {

    void create(CityRequest cityRequest);
    void update(CityRequest cityRequest);
    void delete(Long id);
    City getCityById(Long id);
    City getCityByJobAdvertisementId(Long id);
    CityResponse getCityResponseById(Long id);

}
