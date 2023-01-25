package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.WorkExperienceRequest;
import com.umutdoruk.hrms.DTO.response.WorkExperienceResponse;
import com.umutdoruk.hrms.entities.WorkExperience;

import java.util.List;

public interface WorkExperienceService {

    void add(WorkExperienceRequest workExperienceRequest);
    List<WorkExperienceResponse> findAllByOrderByEndDateDesc();
    List<WorkExperienceResponse> getAll(Long resumeId);
    WorkExperienceResponse getById(Long id);
    WorkExperience findById(Long id);
    void delete(Long id);
    void update(WorkExperienceRequest workExperienceRequest, Long workExperienceId);
}
