package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.concretes.Employer;

import java.util.List;

public interface EmployerService {
    void add(Employer employer);
    List<Employer> getAll();
    Employer findByEmail(String email);
}
