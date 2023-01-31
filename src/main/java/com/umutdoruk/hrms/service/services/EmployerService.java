package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.EmployerRequest;
import com.umutdoruk.hrms.DTO.response.EmployerResponse;
import com.umutdoruk.hrms.entities.Employer;

import java.util.List;

public interface EmployerService {
    void create(EmployerRequest employerRequest);
    void update(EmployerRequest employerRequest);
    /*void delete(Long id);*/
    Employer getEmployerById(Long id);
    EmployerResponse getEmployerResponseById(Long id);
    /*EmployerResponse getEmployerResponseByEmail(String email);*/
    List<EmployerResponse> getAllEmployerResponses();

}
