package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.JobPositionRequest;
import com.umutdoruk.hrms.DTO.response.JobPositionResponse;

import java.util.List;

public interface JobPositionService {
    List<JobPositionResponse> getAll();
    void add(JobPositionRequest jobPositionRequest);
    List<JobPositionResponse> findByName(String jobPositionName);
    void update (JobPositionRequest jobPositionRequest);
    void delete (Long id);
}
