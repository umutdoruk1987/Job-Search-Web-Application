package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.WorkExperience;

import java.util.List;

public interface WorkExperienceService {

    void add(WorkExperience workExperience);
    List<WorkExperience> findAllByOrder();
    List<WorkExperience> getAll();
    WorkExperience getById(Long id);
    void delete(Long id);
    void update(WorkExperience workExperience);
}
