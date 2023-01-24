package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.TechnologyRequest;
import com.umutdoruk.hrms.DTO.response.TechnologyResponse;

import java.util.List;

public interface TechnologyService {

    void add(TechnologyRequest technologyRequest);
    List<TechnologyResponse> getAll();
    TechnologyResponse findById(Long id);
    void delete(Long id);
    void update(TechnologyRequest technologyRequest);
}
