package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.TechnologyRequest;
import com.umutdoruk.jobSearch.dto.response.TechnologyResponse;
import com.umutdoruk.jobSearch.entities.Technology;

import java.util.List;

public interface TechnologyService {

    TechnologyResponse create(TechnologyRequest technologyRequest);
    TechnologyResponse update(TechnologyRequest technologyRequest);
    void delete(Long id);
    Technology getTechnologyById(Long id);
    TechnologyResponse getTechnologyResponseById(Long id);
    List<TechnologyResponse> getAllTechnologiesResponsesInResume(Long resumeId);
}
