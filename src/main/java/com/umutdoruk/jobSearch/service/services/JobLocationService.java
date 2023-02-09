package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.JobLocationRequest;
import com.umutdoruk.jobSearch.dto.response.JobLocationResponse;
import com.umutdoruk.jobSearch.entities.JobLocation;

public interface JobLocationService {

    JobLocationResponse create(JobLocationRequest jobLocationRequest);
    JobLocationResponse update(JobLocationRequest jobLocationRequest);
    void delete(Long id);
    JobLocation getJobLocationById(Long id);
    JobLocation getJobLocationByJobAdvertisementId(Long id);
    JobLocationResponse getJobLocationResponseById(Long id);

}
