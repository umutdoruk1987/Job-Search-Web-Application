package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.JobType;

import java.util.List;

public interface JobTypeService {

    void add(JobType jobType);
    List<JobType> getAll();
    JobType getById(Long id);
    void update (JobType jobType);
    void delete (Long id);
}
