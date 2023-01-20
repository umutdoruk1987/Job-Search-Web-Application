package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.Education;

import java.util.List;

public interface EducationService {

    void add(Education education);
    List<Education> getAll();
    List<Education> findAllByOrderByGraduationDateAsc();
    List<Education> findAllByOrderByGraduationDateDesc();
}
