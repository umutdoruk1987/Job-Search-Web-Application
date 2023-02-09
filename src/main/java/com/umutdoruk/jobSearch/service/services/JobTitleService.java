package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.JobTitleRequest;
import com.umutdoruk.jobSearch.dto.response.JobTitleResponse;
import com.umutdoruk.jobSearch.entities.JobTitle;

import java.util.List;

public interface JobTitleService {

    JobTitleResponse create(JobTitleRequest jobTitleRequest);
    JobTitleResponse update (JobTitleRequest jobTitleRequest);
    void delete (Long id);
    JobTitle getJobTitleById(Long id);
    JobTitle getJobTitleByJobAdvertisementId(Long id);
    JobTitleResponse getJobTitleResponseById(Long id);
    List<JobTitleResponse> getAllJobTitleResponses();
}
