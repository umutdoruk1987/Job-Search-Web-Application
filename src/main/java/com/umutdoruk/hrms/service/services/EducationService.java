package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.Education;

import java.util.List;

public interface EducationService {

    void add(Education education);
    void update(Education education);
    void delete(Long id);
    Education getById(Long id);
    List<Education> getAll();
    List<Education> findAllByOrderByGraduationDateAsc();
    List<Education> findAllByOrderByGraduationDateDesc();
}
