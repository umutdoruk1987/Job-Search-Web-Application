package com.umutdoruk.jobSearch.service.employer;

import com.umutdoruk.jobSearch.dto.employer.EmployerRequest;
import com.umutdoruk.jobSearch.dto.employer.EmployerResponse;
import com.umutdoruk.jobSearch.entities.employer.Employer;

import java.util.List;

public interface EmployerService {
    EmployerResponse create(EmployerRequest employerRequest);
    EmployerResponse update(EmployerRequest employerRequest);
    void delete();
    Employer getEmployerById(Long id);
    Employer getEmployerByUserId(Long userId);
    EmployerResponse getEmployerResponseById(Long id);
    List<EmployerResponse> getAllEmployerResponses();
}
