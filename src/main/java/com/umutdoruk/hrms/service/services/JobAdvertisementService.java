package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementService {

    void add(JobAdvertisement jobAdvertisement);
    void update(JobAdvertisement jobAdvertisement);
    void delete(Long id);
    List<JobAdvertisement> getAll();
    JobAdvertisement getById(Long id);
    List<JobAdvertisement> findByActiveTrue();
    List<JobAdvertisement> findByActiveTrueAndCreateDateAsc();
    List<JobAdvertisement> findByActiveTrueOrderByCreateDateDesc();
    List<JobAdvertisement> findByActiveTrueAndEmployer(Long employerId);
}
