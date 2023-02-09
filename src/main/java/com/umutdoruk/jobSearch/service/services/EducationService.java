package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.EducationRequest;
import com.umutdoruk.jobSearch.dto.response.EducationResponse;
import com.umutdoruk.jobSearch.entities.Education;

import java.util.List;

public interface EducationService {

    EducationResponse create(EducationRequest educationRequest);
    EducationResponse update(EducationRequest educationRequest);
    void delete(Long id);
    Education getEducationById(Long id);
    EducationResponse getEducationResponseById(Long id);
    List<EducationResponse> getAllEducationResponsesInResume(Long resumeId);
    List<EducationResponse> findAllByOrderByGraduationDateAsc();
    List<EducationResponse> findAllByOrderByGraduationDateDesc();
}
