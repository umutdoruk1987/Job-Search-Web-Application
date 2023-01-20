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
        jobTypeRepository.save(jobType);
    }

    @Override
    public List<JobType> getAll() {
        return jobTypeRepository.findAll();
    }

    @Override
    public JobType getById(int id) {
        return jobTypeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Job Type is not found"));
    }
}
