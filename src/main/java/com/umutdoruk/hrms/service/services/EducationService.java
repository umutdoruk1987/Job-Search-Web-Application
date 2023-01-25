package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.EducationRequest;
import com.umutdoruk.hrms.DTO.response.EducationResponse;
import com.umutdoruk.hrms.entities.Education;

import java.util.List;

public interface EducationService {

    void add(EducationRequest educationRequest);
    void update(EducationRequest educationRequest, Long educationId);
    void delete(Long id);
    EducationResponse getById(Long id);
    Education findById(Long id);
    List<EducationResponse> getAll(Long resumeId);
    List<EducationResponse> findAllByOrderByGraduationDateAsc();
    List<EducationResponse> findAllByOrderByGraduationDateDesc();
}
