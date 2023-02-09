package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.EmployerRequest;
import com.umutdoruk.jobSearch.dto.response.EmployerResponse;
import com.umutdoruk.jobSearch.entities.Employer;

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
