package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.JobTypeRequest;
import com.umutdoruk.hrms.DTO.response.JobTypeResponse;
import com.umutdoruk.hrms.entities.JobType;

import java.util.List;

public interface JobTypeService {

    void create(JobTypeRequest jobTypeRequest);
    void update (JobTypeRequest jobTypeRequest);
    void delete (Long id);
    JobType getJobTypeById(Long id);
    JobType getJobTypeByJobAdvertisementId(Long id);
    JobTypeResponse getJobTypeResponseById(Long id);
    List<JobTypeResponse> getAllJobTypeResponses();
}
