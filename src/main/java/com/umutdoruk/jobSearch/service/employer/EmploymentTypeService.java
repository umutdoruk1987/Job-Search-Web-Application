package com.umutdoruk.jobSearch.service.employer;

import com.umutdoruk.jobSearch.dto.employer.EmploymentTypeRequest;
import com.umutdoruk.jobSearch.dto.employer.EmploymentTypeResponse;
import com.umutdoruk.jobSearch.entities.employer.EmploymentType;

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
