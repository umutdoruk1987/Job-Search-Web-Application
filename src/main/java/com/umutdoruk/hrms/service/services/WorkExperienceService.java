package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.concretes.WorkExperience;

import java.util.List;

public interface WorkExperienceService {

    void add(WorkExperience workExperience);
    List<WorkExperience> findAllByOrder();
    List<WorkExperience> getAll();
}
