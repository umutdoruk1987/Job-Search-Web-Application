package com.umutdoruk.jobSearch.service.employer;

import com.umutdoruk.jobSearch.dto.employer.JobAdvertisementRequest;
import com.umutdoruk.jobSearch.dto.employer.JobAdvertisementResponse;
import com.umutdoruk.jobSearch.entities.employer.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementService {

    JobAdvertisementResponse create(JobAdvertisementRequest jobAdvertisementRequest);
    JobAdvertisementResponse update(JobAdvertisementRequest jobAdvertisementRequest);
    void delete(Long id);
    JobAdvertisement getJobAdvertisementById(Long id);
    JobAdvertisementResponse getJobAdvertisementResponseById(Long id);
    List<JobAdvertisementResponse> getAllJobAdvertisements();
    List<JobAdvertisementResponse> getAllJobAdvertisementsByEmployerId(Long employerId);
    List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrue();
    List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc();
    List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc();
    List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndEmployerId(Long employerId);
    /*boolean isJobAdvertisementBelongToEmployer(Long jobAdvertisementId);*/
}
