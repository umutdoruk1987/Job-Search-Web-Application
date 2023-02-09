package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.WorkExperienceRequest;
import com.umutdoruk.jobSearch.dto.response.WorkExperienceResponse;
import com.umutdoruk.jobSearch.entities.WorkExperience;

import java.util.List;

public interface WorkExperienceService {

    WorkExperienceResponse create(WorkExperienceRequest workExperienceRequest);
    WorkExperienceResponse update(WorkExperienceRequest workExperienceRequest);
    void delete(Long id);
    WorkExperience getWorkExperienceById(Long id);
    WorkExperienceResponse getWorkExperienceResponseById(Long id);
    List<WorkExperienceResponse> getAllWorkExperienceResponsesInResume(Long resumeId);
    List<WorkExperienceResponse> getAllWorkExperienceResponsesByOrderByEndDateDesc();
}
