package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.JobPosition;

import java.util.List;

public interface JobPositionService {
    List<JobPosition> getAll();
    void add(JobPosition jobPosition);
    List<JobPosition> findByName(String jobPositionName);
    void update (JobPosition jobPosition);
    void delete (Long id);

}
