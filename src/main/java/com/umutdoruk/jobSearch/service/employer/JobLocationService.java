package com.umutdoruk.jobSearch.service.employer;

import com.umutdoruk.jobSearch.dto.employer.JobLocationRequest;
import com.umutdoruk.jobSearch.dto.employer.JobLocationResponse;
import com.umutdoruk.jobSearch.entities.employer.JobLocation;

public interface JobLocationService {

    JobLocationResponse create(JobLocationRequest jobLocationRequest);
    JobLocationResponse update(JobLocationRequest jobLocationRequest);
    void delete(Long id);
    JobLocation getJobLocationById(Long id);
    JobLocation getJobLocationByJobAdvertisementId(Long id);
    JobLocationResponse getJobLocationResponseById(Long id);

}
