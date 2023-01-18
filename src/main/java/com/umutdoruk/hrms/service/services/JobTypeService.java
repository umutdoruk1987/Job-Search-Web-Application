package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.concretes.JobType;

import java.util.List;

public interface JobTypeService {

    void add(JobType jobType);
    List<JobType> getAll();
    JobType getById(int id);
}
