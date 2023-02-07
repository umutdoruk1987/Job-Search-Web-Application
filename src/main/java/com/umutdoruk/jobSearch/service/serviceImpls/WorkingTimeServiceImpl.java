package com.umutdoruk.jobSearch.service.serviceImpls;

import com.umutdoruk.jobSearch.dto.request.WorkingTimeRequest;
import com.umutdoruk.jobSearch.dto.response.WorkingTimeResponse;
import com.umutdoruk.jobSearch.entities.Employer;
import com.umutdoruk.jobSearch.entities.WorkingTime;
import com.umutdoruk.jobSearch.entities.User;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.WorkingTimeRepository;
import com.umutdoruk.jobSearch.service.services.EmployerService;
import com.umutdoruk.jobSearch.service.services.JobAdvertisementService;
import com.umutdoruk.jobSearch.service.services.WorkingTimeService;
import com.umutdoruk.jobSearch.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkingTimeServiceImpl implements WorkingTimeService {
    private final WorkingTimeRepository workingTimeRepository;
    private final JobAdvertisementService jobAdvertisementService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private UserService userService;


    @Autowired
    public WorkingTimeServiceImpl(WorkingTimeRepository workingTimeRepository, JobAdvertisementService jobAdvertisementService) {
        this.workingTimeRepository = workingTimeRepository;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @Override
    public List<WorkingTimeResponse> getAllWorkingTimeResponses() {
        if (workingTimeRepository.findAll().isEmpty())
            throw new NotFoundException("Any Working Time record isn't found");

        return workingTimeRepository.findAll()
                .stream()
                .map(workingTime -> WorkingTimeResponse.of(workingTime))
                .collect(Collectors.toList());
    }

    @Override
    public void create(WorkingTimeRequest workingTimeRequest) {
        if (workingTimeRequest == null) throw new NotFoundException("No Working Time record found to add");

        if (getWorkingTimeByJobAdvertisementId(workingTimeRequest.getJobAdvertisementId())!= null)
            throw new BadRequestException("You have already created a Working Time. You can update it.");

        WorkingTime workingTime = new WorkingTime();
        workingTime.setName(workingTimeRequest.getName());
        workingTime.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(workingTimeRequest.getJobAdvertisementId()));

        workingTimeRepository.save(workingTime);
    }

    @Override
    public void update(WorkingTimeRequest workingTimeRequest) {

        if (workingTimeRequest == null)
            throw new NotFoundException("No Working Time record found to update");
        if (!isWorkingTimeBelongedToJobAdvertisement(workingTimeRequest.getWorkingTimeId()))
            throw new BadRequestException("You have no such job position");

        WorkingTime workingTime = getWorkingTimeById(workingTimeRequest.getWorkingTimeId());
        workingTime.setName(workingTimeRequest.getName());
        /*jobPosition.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(jobPositionRequest.getJobAdvertisementId()));*/
        workingTimeRepository.save(workingTime);
    }

    @Override
    public void delete(Long id) {
//        if (!(jobPositionRepository.existsById(id)))
//            throw new NotFoundException("Job Position is not found");
        if (!isWorkingTimeBelongedToJobAdvertisement(id)) throw new BadRequestException("You have no such Working Time");
        workingTimeRepository.deleteById(id);
    }

    @Override
    public WorkingTime getWorkingTimeById(Long workingTimeId) {
        return workingTimeRepository.findById(workingTimeId)
                .orElseThrow(()-> new NotFoundException("Working Time is not found"));
    }

    @Override
    public WorkingTime getWorkingTimeByJobAdvertisementId(Long id) {
        return workingTimeRepository.findByJobAdvertisementId(id);
    }

    @Override
    public WorkingTimeResponse getWorkingTimeResponseById(Long workingTimeId) {
        return WorkingTimeResponse.of(getWorkingTimeById(workingTimeId));
    }

    @Override
    public List<WorkingTimeResponse> getWorkingTimeResponseByName(String workingTimeName) {

        List<WorkingTime> workingTimeList = workingTimeRepository.findByName(workingTimeName)
                .orElseThrow(()-> new NotFoundException("No Working Time record found with name: " + workingTimeName));

        return workingTimeList.stream()
                .map(workingTime -> WorkingTimeResponse.of(workingTime))
                .collect(Collectors.toList());
    }

    private boolean isWorkingTimeBelongedToJobAdvertisement(Long workingTimeId){
        User user = userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication());
        Employer employer = employerService.getEmployerByUserId(user.getId());
        long count = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employer.getId())
                .stream()
                .filter(jobAdvertisementResponse -> jobAdvertisementResponse.getWorkingTimeResponse().getWorkingTimeId().equals(workingTimeId))
                .count();
        return count==1;
    }

}