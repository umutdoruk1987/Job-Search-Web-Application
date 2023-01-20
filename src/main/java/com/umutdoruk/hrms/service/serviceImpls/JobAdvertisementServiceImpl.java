package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.JobAdvertisement;
import com.umutdoruk.hrms.repository.JobAdvertisementRepository;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    private final JobAdvertisementRepository jobAdvertisementRepository;

    @Autowired
    public JobAdvertisementServiceImpl(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    @Override
    public void add(JobAdvertisement jobAdvertisement) {
        jobAdvertisementRepository.save(jobAdvertisement);
    }

    @Override
    public void update(JobAdvertisement jobAdvertisement) {
        jobAdvertisementRepository.save(jobAdvertisement);
    }

    @Override
    public List<JobAdvertisement> findByActiveTrue() {
        return jobAdvertisementRepository.findByActiveTrue();
    }

    @Override
    public List<JobAdvertisement> findByActiveTrueAndCreateDateAsc() {
        return jobAdvertisementRepository.findByActiveTrueOrderByCreateDateAsc();
    }

    @Override
    public List<JobAdvertisement> findByActiveTrueOrderByCreateDateDesc() {
        return jobAdvertisementRepository.findByActiveTrueOrderByCreateDateDesc();
    }

    @Override
    public List<JobAdvertisement> findByActiveTrueAndEmployer(int employerId) {
        return jobAdvertisementRepository.findByActiveTrueAndEmployerId(employerId);
    }

    @Override
    public List<JobAdvertisement> getAll() {
        return jobAdvertisementRepository.findAll();
    }

    @Override
    public JobAdvertisement getById(int id) {
        return jobAdvertisementRepository.findById(id);
    }

}
