package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.JobAdvertisementRequest;
import com.umutdoruk.hrms.DTO.response.JobAdvertisementResponse;
import com.umutdoruk.hrms.entities.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementService {

    void add(JobAdvertisementRequest jobAdvertisementRequest);
    void update(JobAdvertisementRequest jobAdvertisementRequest);
    void delete(Long id);
    List<JobAdvertisementResponse> getAll();
    JobAdvertisementResponse getById(Long id);
    JobAdvertisement findById(Long id);
    List<JobAdvertisementResponse> findByActiveTrue();
    List<JobAdvertisementResponse> findByActiveTrueAndCreatedDateAsc();
    List<JobAdvertisementResponse> findByActiveTrueOrderByCreatedDateDesc();
    List<JobAdvertisementResponse> findByActiveTrueAndEmployer(Long employerId);
}
