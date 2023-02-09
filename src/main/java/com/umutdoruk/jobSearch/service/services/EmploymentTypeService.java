package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.EmploymentTypeRequest;
import com.umutdoruk.jobSearch.dto.response.EmploymentTypeResponse;
import com.umutdoruk.jobSearch.entities.EmploymentType;

import java.util.List;

public interface EmploymentTypeService {

    EmploymentTypeResponse create(EmploymentTypeRequest employmentTypeRequest);
    EmploymentTypeResponse update(EmploymentTypeRequest employmentTypeRequest);
    void delete(Long id);
    EmploymentType getEmploymentTypeById(Long id);
    EmploymentType getEmploymentTypeByJobAdvertisementId(Long id);
    EmploymentTypeResponse getEmploymentTypeResponseById(Long id);
    List<EmploymentTypeResponse> getAllEmploymentTypeResponses();
}
