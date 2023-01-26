package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.JobTypeRequest;
import com.umutdoruk.hrms.DTO.response.JobTypeResponse;
import com.umutdoruk.hrms.entities.JobType;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.JobTypeRepository;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import com.umutdoruk.hrms.service.services.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class JobTypeServiceImpl implements JobTypeService {

    private final JobTypeRepository jobTypeRepository;
    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobTypeServiceImpl(JobTypeRepository jobTypeRepository, JobAdvertisementService jobAdvertisementService) {
        this.jobTypeRepository = jobTypeRepository;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @Override
    public void create(JobTypeRequest jobTypeRequest) {
        if (jobTypeRequest == null) {
            throw new NotFoundException("No Job Type record found to add");
        }

        JobType jobType = new JobType();
        jobType.setName(jobTypeRequest.getName());
        jobType.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(jobTypeRequest.getJobAdvertisementId()));

        jobTypeRepository.save(jobType);
    }

    @Override
    public void update(JobTypeRequest jobTypeRequest, Long typeOfWorksId) {
        JobType jobType = jobTypeRepository.findById(typeOfWorksId)
                .orElseThrow(()-> new NotFoundException("No Job Type with this Id in Repository"));

        if (jobTypeRequest == null)
            throw new NotFoundException("No Job Type record found to update");

        jobType.setName(jobTypeRequest.getName());
        jobType.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(jobTypeRequest.getJobAdvertisementId()));

        jobTypeRepository.save(jobType);
    }

    @Override
    public void delete(Long id) {
        if (!(jobTypeRepository.existsById(id)))
            throw new NotFoundException("Job Type is not found");
        jobTypeRepository.deleteById(id);
    }

    @Override
    public JobType getJobTypeById(Long id) {
        return jobTypeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Job Type is not found"));
    }

    @Override
    public JobTypeResponse getJobTypeResponseById(Long id) {
        return JobTypeResponse.of(getJobTypeById(id));
    }

    @Override
    public List<JobTypeResponse> getAllJobTypeResponses() {

        if (jobTypeRepository.findAll().isEmpty())
            throw new NotFoundException("Any Job Type record isn't found");

        return jobTypeRepository.findAll()
                .stream()
                .map(jobType -> JobTypeResponse.of(jobType))
                .collect(Collectors.toList());
    }
}
