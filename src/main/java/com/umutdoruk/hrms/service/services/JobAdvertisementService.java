package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.concretes.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementService {

    void add(JobAdvertisement jobAdvertisement);
    void update(JobAdvertisement jobAdvertisement);
    List<JobAdvertisement> getAll();
    JobAdvertisement getById(int id);
    List<JobAdvertisement> findByActiveTrue();
    List<JobAdvertisement> findByActiveTrueAndCreateDateAsc();
    List<JobAdvertisement> findByActiveTrueOrderByCreateDateDesc();
    List<JobAdvertisement> findByActiveTrueAndEmployer(int employerId);
}
