package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.TechnologyRequest;
import com.umutdoruk.hrms.DTO.response.TechnologyResponse;
import com.umutdoruk.hrms.entities.Technology;

import java.util.List;

public interface TechnologyService {

    void create(TechnologyRequest technologyRequest);
    void update(TechnologyRequest technologyRequest);
    void delete(Long id);
    Technology getTechnologyById(Long id);
    TechnologyResponse getTechnologyResponseById(Long id);
    List<TechnologyResponse> getAllTechnologiesResponsesInResume(Long resumeId);
}
