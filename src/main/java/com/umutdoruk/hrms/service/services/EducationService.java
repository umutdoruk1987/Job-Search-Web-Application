package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.EducationRequest;
import com.umutdoruk.hrms.DTO.response.EducationResponse;
import com.umutdoruk.hrms.entities.Education;

import java.util.List;

public interface EducationService {

    void create(EducationRequest educationRequest);
    void update(EducationRequest educationRequest, Long educationId);
    void delete(Long id);
    Education getEducationById(Long id);
    EducationResponse getEducationResponseById(Long id);
    List<EducationResponse> getAllEducationResponsesInResume(Long resumeId);
    List<EducationResponse> findAllByOrderByGraduationDateAsc();
    List<EducationResponse> findAllByOrderByGraduationDateDesc();
}
