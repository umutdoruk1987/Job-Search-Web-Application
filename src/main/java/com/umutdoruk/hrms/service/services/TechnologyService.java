package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.TechnologyRequest;
import com.umutdoruk.hrms.DTO.response.TechnologyResponse;
import com.umutdoruk.hrms.entities.Technology;

import java.util.List;

public interface TechnologyService {

    void add(TechnologyRequest technologyRequest);
    List<TechnologyResponse> getAll(Long resumeId);
    TechnologyResponse findById(Long id);
    Technology getById(Long id);
    void delete(Long id);
    void update(TechnologyRequest technologyRequest, Long technologyId);
}
