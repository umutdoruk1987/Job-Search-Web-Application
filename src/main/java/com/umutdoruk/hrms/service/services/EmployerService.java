package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.Employer;

import java.util.List;

public interface EmployerService {
    void add(Employer employer);
    List<Employer> getAll();
    Employer findByEmail(String email);
    Employer findById(Long id);
    void update(Employer employer);
    void delete(Long id);
    Boolean isUserExist(Employer employer);
}
