package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.concretes.JobPosition;

import java.util.List;

public interface JobPositionService {
    List<JobPosition> getAll();
    void add(JobPosition jobPosition);
    List<JobPosition> findByName(String jobPositionName);
}
