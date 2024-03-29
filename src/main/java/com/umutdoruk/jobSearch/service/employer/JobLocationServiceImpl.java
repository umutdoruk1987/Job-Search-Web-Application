package com.umutdoruk.jobSearch.service.employer;

import com.umutdoruk.jobSearch.dto.employer.JobLocationRequest;
import com.umutdoruk.jobSearch.dto.employer.JobLocationResponse;
import com.umutdoruk.jobSearch.entities.employer.JobLocation;
import com.umutdoruk.jobSearch.entities.employer.Employer;
import com.umutdoruk.jobSearch.entities.user.User;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.employer.JobLocationRepository;
import com.umutdoruk.jobSearch.service.user.UserServiceImpl;
import com.umutdoruk.jobSearch.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobLocationServiceImpl implements JobLocationService {

    @Autowired
    private JobLocationRepository jobLocationRepository;
    @Autowired
    private JobAdvertisementService jobAdvertisementService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private UserService userService;

    @Override
    public JobLocationResponse create(JobLocationRequest jobLocationRequest) {

        if (jobLocationRequest == null)  throw new NotFoundException("No Job Location record found to add");

        if (getJobLocationByJobAdvertisementId(jobLocationRequest.getJobAdvertisementId())!= null)
            throw new BadRequestException("You have already created a Job Location. You can update it.");

        /*if (getCityByJobAdvertisementId(cityRequest.getJobAdvertisementId())!= null)
            throw new BadRequestException ("You have already created a city. You can update it.");*/


        JobLocation jobLocation = new JobLocation();
        jobLocation.setJobLocationName(jobLocationRequest.getJobLocationName());
        jobLocation.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(jobLocationRequest.getJobAdvertisementId()));

        return JobLocationResponse.of(jobLocationRepository.save(jobLocation));
    }

    @Override
    public JobLocationResponse update(JobLocationRequest jobLocationRequest) {

        if (jobLocationRequest == null)
            throw new NotFoundException("No Job Location record found to update");
        if (!isJobLocationBelongedToJobAdvertisement(jobLocationRequest.getJobLocationId()))
            throw new BadRequestException("You have no such Job Location");

        JobLocation jobLocation = getJobLocationById(jobLocationRequest.getJobLocationId());
        jobLocation.setJobLocationName(jobLocationRequest.getJobLocationName());
        /*city.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(cityRequest.getJobAdvertisementId()));*/
        return JobLocationResponse.of(jobLocationRepository.save(jobLocation));
    }

    @Override
    public void delete(Long id) {
        if (!isJobLocationBelongedToJobAdvertisement(id)) throw new BadRequestException("You have no such Job Location");
        /*if (!(cityRepository.existsById(id)))
            throw new NotFoundException("No City found to delete");*/
        jobLocationRepository.deleteById(id);
    }

    @Override
    public JobLocation getJobLocationById(Long id) {
        return jobLocationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Job Location is not found"));
    }

    @Override
    public JobLocation getJobLocationByJobAdvertisementId(Long id) {
        return jobLocationRepository.findByJobAdvertisementId(id);
    }

    @Override
    public JobLocationResponse getJobLocationResponseById(Long id) {
        return JobLocationResponse.of(getJobLocationById(id));
    }

    private boolean isJobLocationBelongedToJobAdvertisement(Long jobLocationId){
        User user = userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication());
        Employer employer = employerService.getEmployerByUserId(user.getId());
        long count = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employer.getId())
                .stream()
                .filter(jobAdvertisementResponse -> jobAdvertisementResponse.getJobLocationResponse().getJobLocationId().equals(jobLocationId))
                .count();

        return count==1;
    }
}

