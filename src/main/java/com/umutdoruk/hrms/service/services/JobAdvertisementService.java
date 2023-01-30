package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.JobAdvertisementRequest;
import com.umutdoruk.hrms.DTO.response.JobAdvertisementResponse;
import com.umutdoruk.hrms.entities.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementService {

    void create(JobAdvertisementRequest jobAdvertisementRequest);
    void update(JobAdvertisementRequest jobAdvertisementRequest);
    void delete(Long id);
    JobAdvertisement getJobAdvertisementById(Long id);
    JobAdvertisementResponse getJobAdvertisementResponseById(Long id);
    List<JobAdvertisementResponse> getAllJobAdvertisements();
    List<JobAdvertisementResponse> getAllJobAdvertisementsByEmployerId(Long employerId);
    List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrue();
    List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc();
    List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc();
    List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndEmployerId(Long employerId);
}
