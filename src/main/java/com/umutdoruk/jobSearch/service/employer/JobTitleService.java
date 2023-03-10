package com.umutdoruk.jobSearch.service.employer;

import com.umutdoruk.jobSearch.dto.employer.JobTitleRequest;
import com.umutdoruk.jobSearch.dto.employer.JobTitleResponse;
import com.umutdoruk.jobSearch.entities.employer.JobTitle;

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
