package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.EducationRequest;
import com.umutdoruk.jobSearch.dto.candidate.EducationResponse;
import com.umutdoruk.jobSearch.entities.candidate.Education;

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
