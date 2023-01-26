package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.JobPositionRequest;
import com.umutdoruk.hrms.DTO.response.JobPositionResponse;
import com.umutdoruk.hrms.entities.JobPosition;

import java.util.List;

public interface JobPositionService {
    void create(JobPositionRequest jobPositionRequest);
    void update (JobPositionRequest jobPositionRequest, Long jobPositionId);
    void delete (Long id);
    JobPosition getJobPositionById(Long jobPositionId);
    JobPositionResponse getJobPositionResponseById(Long jobPositionId);
    List<JobPositionResponse> getJobPositionResponseByName(String jobPositionName);
    List<JobPositionResponse> getAllJobPositionResponses();
}
