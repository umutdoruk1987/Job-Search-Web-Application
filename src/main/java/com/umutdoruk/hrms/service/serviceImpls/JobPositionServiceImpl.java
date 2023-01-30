package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.JobPositionRequest;
import com.umutdoruk.hrms.DTO.response.JobPositionResponse;
import com.umutdoruk.hrms.entities.JobPosition;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.JobPositionRepository;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import com.umutdoruk.hrms.service.services.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPositionServiceImpl implements JobPositionService {
    private final JobPositionRepository jobPositionRepository;
    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobPositionServiceImpl(JobPositionRepository jobPositionRepository, JobAdvertisementService jobAdvertisementService) {
        this.jobPositionRepository = jobPositionRepository;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @Override
    public List<JobPositionResponse> getAllJobPositionResponses() {
        if (jobPositionRepository.findAll().isEmpty())
            throw new NotFoundException("Any Job Position record isn't found");

        return jobPositionRepository.findAll()
                .stream()
                .map(jobPosition -> JobPositionResponse.of(jobPosition))
                .collect(Collectors.toList());
    }

    @Override
    public void create(JobPositionRequest jobPositionRequest) {
        if (jobPositionRequest == null) {
            throw new NotFoundException("No Job Position record found to add");
        }

        JobPosition jobPosition = new JobPosition();
        jobPosition.setName(jobPositionRequest.getName());
        jobPosition.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(jobPositionRequest.getJobAdvertisementId()));

        jobPositionRepository.save(jobPosition);
    }

    @Override
    public void update(JobPositionRequest jobPositionRequest) {

        if (jobPositionRequest == null)
            throw new NotFoundException("No Job Position record found to update");

        JobPosition jobPosition = jobPositionRepository.findById(jobPositionRequest.getJobPositionId())
                .orElseThrow(()-> new NotFoundException("No Job Position with this Id in Repository"));

        jobPosition.setName(jobPositionRequest.getName());
        jobPosition.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(jobPositionRequest.getJobAdvertisementId()));

        jobPositionRepository.save(jobPosition);
    }

    @Override
    public void delete(Long id) {
        if (!(jobPositionRepository.existsById(id)))
            throw new NotFoundException("Job Position is not found");
        jobPositionRepository.deleteById(id);
    }

    @Override
    public JobPosition getJobPositionById(Long jobPositionId) {
        return jobPositionRepository.findById(jobPositionId)
                .orElseThrow(()-> new NotFoundException("Job Position is not found"));
    }

    @Override
    public JobPositionResponse getJobPositionResponseById(Long jobPositionId) {
        return JobPositionResponse.of(getJobPositionById(jobPositionId));
    }

    @Override
    public List<JobPositionResponse> getJobPositionResponseByName(String jobPositionName) {

        List<JobPosition> jobPositionList = jobPositionRepository.findByName(jobPositionName)
                .orElseThrow(()-> new NotFoundException("No Job Position record found with name: " + jobPositionName));

        return jobPositionList.stream()
                .map(jobPosition -> JobPositionResponse.of(jobPosition))
                .collect(Collectors.toList());
    }

}