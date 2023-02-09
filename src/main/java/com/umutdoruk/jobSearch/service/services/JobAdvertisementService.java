package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.JobAdvertisementRequest;
import com.umutdoruk.jobSearch.dto.response.JobAdvertisementResponse;
import com.umutdoruk.jobSearch.entities.JobAdvertisement;

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
