package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.CityRequest;
import com.umutdoruk.hrms.DTO.response.CityResponse;

public interface CityService {

    void create(CityRequest cityRequest);
    void update(CityRequest cityRequest, Long cityId);
    void delete(Long id);
    CityResponse getById(Long id);
}
