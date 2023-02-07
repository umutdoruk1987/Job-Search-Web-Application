package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.JobLocationRequest;
import com.umutdoruk.jobSearch.dto.response.JobLocationResponse;
import com.umutdoruk.jobSearch.entities.JobLocation;

public interface JobLocationService {

    void create(JobLocationRequest jobLocationRequest);
    void update(JobLocationRequest jobLocationRequest);
    void delete(Long id);
    JobLocation getJobLocationById(Long id);
    JobLocation getJobLocationByJobAdvertisementId(Long id);
    JobLocationResponse getJobLocationResponseById(Long id);

}
