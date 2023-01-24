package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.EducationRequest;
import com.umutdoruk.hrms.DTO.response.EducationResponse;

import java.util.List;

public interface EducationService {

    void add(EducationRequest educationRequest);
    void update(EducationRequest educationRequest);
    void delete(Long id);
    EducationResponse getById(Long id);
    List<EducationResponse> getAll();
    List<EducationResponse> findAllByOrderByGraduationDateAsc();
    List<EducationResponse> findAllByOrderByGraduationDateDesc();
}
