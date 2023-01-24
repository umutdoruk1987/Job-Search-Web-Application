package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.EmployerRequest;
import com.umutdoruk.hrms.DTO.response.EmployerResponse;

import java.util.List;

public interface EmployerService {
    void add(EmployerRequest employerRequest);
    List<EmployerResponse> getAll();
    EmployerResponse findByEmail(String email);
    EmployerResponse findById(Long id);
    void update(EmployerRequest employerRequest);
    void delete(Long id);
    Boolean isUserExist(EmployerRequest employerRequest);
}
