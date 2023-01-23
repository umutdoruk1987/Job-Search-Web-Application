package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.JobAdvertisement;
import com.umutdoruk.hrms.exception.NotFoundException;
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
        if (jobAdvertisement == null) {
            throw new NotFoundException("No job Advertisement record found to add");
        }

        jobAdvertisementRepository.save(jobAdvertisement);
    }

    @Override
    public void update(JobAdvertisement jobAdvertisement) {

        JobAdvertisement jobAdvertisementToUpdate = jobAdvertisementRepository.findById(jobAdvertisement.getId())
                .orElseThrow(()-> new NotFoundException("Job Advertisement is not found"));

        jobAdvertisementToUpdate.setJobPosition(jobAdvertisement.getJobPosition());
        jobAdvertisementToUpdate.setJobType(jobAdvertisement.getJobType());
        jobAdvertisementToUpdate.setCity(jobAdvertisement.getCity());
        jobAdvertisementToUpdate.setMinSalary(jobAdvertisement.getMinSalary());
        jobAdvertisementToUpdate.setMaxSalary(jobAdvertisement.getMaxSalary());
        jobAdvertisementToUpdate.setNumberOfOpenJobPosition(jobAdvertisement.getNumberOfOpenJobPosition());
        jobAdvertisementToUpdate.setApplicationDeadline(jobAdvertisement.getApplicationDeadline());
        jobAdvertisementToUpdate.setDescription(jobAdvertisement.getDescription());
        jobAdvertisementToUpdate.setTypeOfWork(jobAdvertisement.getTypeOfWork());
        jobAdvertisementToUpdate.setCity(jobAdvertisement.getCity());

        jobAdvertisementRepository.save(jobAdvertisementToUpdate);
    }

    @Override
    public JobAdvertisement getById(Long id) {
        return jobAdvertisementRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Job Advertisement is not found"));
    }

    @Override
    public void delete(Long id) {
        if (!(jobAdvertisementRepository.existsById(id)))
            throw new NotFoundException("Job Advertisement is not found");

        jobAdvertisementRepository.deleteById(id);
    }

    @Override
    public List<JobAdvertisement> findByActiveTrue() {
        return jobAdvertisementRepository.findByActiveTrue();
    }

    @Override
    public List<JobAdvertisement> findByActiveTrueAndCreatedDateAsc() {
        return jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateAsc();
    }

    @Override
    public List<JobAdvertisement> findByActiveTrueOrderByCreatedDateDesc() {
        return jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateDesc();
    }

    @Override
    public List<JobAdvertisement> findByActiveTrueAndEmployer(Long employerId) {
        return jobAdvertisementRepository.findByActiveTrueAndEmployerId(employerId);
    }

    @Override
    public List<JobAdvertisement> getAll() {
        return jobAdvertisementRepository.findAll();
    }


}
