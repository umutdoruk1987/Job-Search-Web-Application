package com.umutdoruk.jobSearch.service.serviceImpls;

import com.umutdoruk.jobSearch.dto.request.JobTitleRequest;
import com.umutdoruk.jobSearch.dto.response.JobTitleResponse;
import com.umutdoruk.jobSearch.entities.Employer;
import com.umutdoruk.jobSearch.entities.JobTitle;
import com.umutdoruk.jobSearch.entities.User;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.JobTitleRepository;
import com.umutdoruk.jobSearch.service.services.EmployerService;
import com.umutdoruk.jobSearch.service.services.JobAdvertisementService;
import com.umutdoruk.jobSearch.service.services.JobTitleService;
import com.umutdoruk.jobSearch.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository jobTitleRepository;
    private final JobAdvertisementService jobAdvertisementService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private UserService userService;

    @Autowired
    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository, JobAdvertisementService jobAdvertisementService) {
        this.jobTitleRepository = jobTitleRepository;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @Override
    public void create(JobTitleRequest jobTitleRequest) {
        if (jobTitleRequest == null)  throw new NotFoundException("No Job Title record found to add");

        if (getJobTitleByJobAdvertisementId(jobTitleRequest.getJobAdvertisementId())!= null)
            throw new BadRequestException("You have already created a job title. You can update it.");

        JobTitle jobTitle = new JobTitle();
        jobTitle.setName(jobTitleRequest.getName());
        jobTitle.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(jobTitleRequest.getJobAdvertisementId()));

        jobTitleRepository.save(jobTitle);
    }

    @Override
    public void update(JobTitleRequest jobTitleRequest) {

        if (jobTitleRequest == null)
            throw new NotFoundException("No Job Title record found to update");
        if (!isJobTitleBelongedToJobAdvertisement(jobTitleRequest.getJobTitleId()))
            throw new BadRequestException("You have no such job Title");

        JobTitle jobTitle = getJobTitleById(jobTitleRequest.getJobTitleId());
        jobTitle.setName(jobTitleRequest.getName());
       /* jobType.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(jobTypeRequest.getJobAdvertisementId()));*/
        jobTitleRepository.save(jobTitle);
    }

    @Override
    public void delete(Long id) {
        /*if (!(jobTypeRepository.existsById(id)))
            throw new NotFoundException("Job Type is not found");*/
        if (!isJobTitleBelongedToJobAdvertisement(id)) throw new BadRequestException("You have no such job title");
        jobTitleRepository.deleteById(id);
    }

    @Override
    public JobTitle getJobTitleById(Long id) {
        return jobTitleRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Job Title is not found"));
    }

    @Override
    public JobTitle getJobTitleByJobAdvertisementId(Long id) {
        return jobTitleRepository.findByJobAdvertisementId(id);
    }

    @Override
    public JobTitleResponse getJobTitleResponseById(Long id) {
        return JobTitleResponse.of(getJobTitleById(id));
    }

    @Override
    public List<JobTitleResponse> getAllJobTitleResponses() {

        if (jobTitleRepository.findAll().isEmpty())
            throw new NotFoundException("Any Job Title record isn't found");

        return jobTitleRepository.findAll()
                .stream()
                .map(jobTitle -> JobTitleResponse.of(jobTitle))
                .collect(Collectors.toList());
    }

    private boolean isJobTitleBelongedToJobAdvertisement(Long jobTitleId){
        User user = userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication());
        Employer employer = employerService.getEmployerByUserId(user.getId());
        long count = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employer.getId())
                .stream()
                .filter(jobAdvertisementResponse -> jobAdvertisementResponse.getJobTitleResponse().getJobTitleId().equals(jobTitleId))
                .count();
        return count==1;
    }
}
