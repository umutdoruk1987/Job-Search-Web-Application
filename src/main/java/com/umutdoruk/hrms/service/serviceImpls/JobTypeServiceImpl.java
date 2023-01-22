package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.JobType;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.JobTypeRepository;
import com.umutdoruk.hrms.service.services.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class JobTypeServiceImpl implements JobTypeService {

    private final JobTypeRepository jobTypeRepository;

    @Autowired
    public JobTypeServiceImpl(JobTypeRepository jobTypeRepository) {
        this.jobTypeRepository = jobTypeRepository;
    }

    @Override
    public void add(JobType jobType) {
        if (jobType == null) {
            throw new NotFoundException("No Job Type record found to add");
        }
        jobTypeRepository.save(jobType);
    }

    @Override
    public List<JobType> getAll() {
        return jobTypeRepository.findAll();
    }

    @Override
    public JobType getById(Long id) {
        return jobTypeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Job Type is not found"));
    }

    @Override
    public void update(JobType jobType) {
        JobType jobTypeToUpdate = jobTypeRepository.findById(jobType.getJobTypeId())
                .orElseThrow(()-> new NotFoundException("Job Type is not found"));

        jobTypeToUpdate.setName(jobType.getName());
        jobTypeRepository.save(jobTypeToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!(jobTypeRepository.existsById(id)))
            throw new NotFoundException("Job Type is not found");
        jobTypeRepository.deleteById(id);
    }
}
