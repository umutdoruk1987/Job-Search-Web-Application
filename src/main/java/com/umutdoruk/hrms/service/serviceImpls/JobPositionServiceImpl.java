package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.JobPosition;
import com.umutdoruk.hrms.repository.JobPositionRepository;
import com.umutdoruk.hrms.service.services.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionServiceImpl implements JobPositionService {
    private final JobPositionRepository jobPositionRepository;

    @Autowired
    public JobPositionServiceImpl(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    @Override
    public List<JobPosition> getAll() {
        return jobPositionRepository.findAll();
    }

    @Override
    public void add(JobPosition jobPosition) {
        jobPositionRepository.save(jobPosition);
    }

    @Override
    public List<JobPosition> findByName(String jobPositionName) {
        return jobPositionRepository.findByName(jobPositionName);
    }

}