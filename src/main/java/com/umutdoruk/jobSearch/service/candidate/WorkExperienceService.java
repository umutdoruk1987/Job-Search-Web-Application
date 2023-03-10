package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.WorkExperienceRequest;
import com.umutdoruk.jobSearch.dto.candidate.WorkExperienceResponse;
import com.umutdoruk.jobSearch.entities.candidate.WorkExperience;

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
