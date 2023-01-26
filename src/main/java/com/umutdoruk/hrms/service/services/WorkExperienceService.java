package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.WorkExperienceRequest;
import com.umutdoruk.hrms.DTO.response.WorkExperienceResponse;
import com.umutdoruk.hrms.entities.WorkExperience;

import java.util.List;

public interface WorkExperienceService {

    void create(WorkExperienceRequest workExperienceRequest);
    void update(WorkExperienceRequest workExperienceRequest, Long workExperienceId);
    void delete(Long id);
    WorkExperience getWorkExperienceById(Long id);
    WorkExperienceResponse getWorkExperienceResponseById(Long id);
    List<WorkExperienceResponse> getAllWorkExperienceResponsesInResume(Long resumeId);
    List<WorkExperienceResponse> getAllWorkExperienceResponsesByOrderByEndDateDesc();
}
