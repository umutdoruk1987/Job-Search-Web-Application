package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.JobPositionRequest;
import com.umutdoruk.hrms.DTO.response.JobPositionResponse;

import java.util.List;

public interface JobPositionService {
    List<JobPositionResponse> getAll();
    void add(JobPositionRequest jobPositionRequest);
    JobPositionResponse findById(Long jobPositionId);
    List<JobPositionResponse> findByName(String jobPositionName);
    void update (JobPositionRequest jobPositionRequest, Long jobPositionId);
    void delete (Long id);
}
