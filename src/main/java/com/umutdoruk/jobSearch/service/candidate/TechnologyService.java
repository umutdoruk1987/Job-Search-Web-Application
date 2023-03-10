package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.TechnologyRequest;
import com.umutdoruk.jobSearch.dto.candidate.TechnologyResponse;
import com.umutdoruk.jobSearch.entities.candidate.Technology;

import java.util.List;

public interface TechnologyService {

    TechnologyResponse create(TechnologyRequest technologyRequest);
    TechnologyResponse update(TechnologyRequest technologyRequest);
    void delete(Long id);
    Technology getTechnologyById(Long id);
    TechnologyResponse getTechnologyResponseById(Long id);
    List<TechnologyResponse> getAllTechnologiesResponsesInResume(Long resumeId);
}
