package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.JobTypeRequest;
import com.umutdoruk.hrms.DTO.response.JobTypeResponse;

import java.util.List;

public interface JobTypeService {

    void add(JobTypeRequest jobTypeRequest);
    List<JobTypeResponse> getAll();
    JobTypeResponse getById(Long id);
    void update (JobTypeRequest jobTypeRequest, Long jobTypeId);
    void delete (Long id);
}
