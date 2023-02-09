package com.umutdoruk.jobSearch.service.serviceImpls;

import com.umutdoruk.jobSearch.dto.request.JobAdvertisementRequest;
import com.umutdoruk.jobSearch.dto.response.*;
import com.umutdoruk.jobSearch.entities.*;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.JobAdvertisementRepository;
import com.umutdoruk.jobSearch.service.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    @Autowired
    private JobAdvertisementRepository jobAdvertisementRepository;
    @Autowired
    private JobLocationService jobLocationService;
    @Autowired
    private WorkingTimeService workingTimeService;
    @Autowired
    private JobTitleService jobTitleService;
    @Autowired
    private EmploymentTypeService employmentTypeService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private UserService userService;

    @Override
    public JobAdvertisementResponse create(JobAdvertisementRequest jobAdvertisementRequest) {
        JobAdvertisement jobAdvertisement = jobAdvertisementCreator(jobAdvertisementRequest);
        return JobAdvertisementResponse.of(jobAdvertisementRepository.save(jobAdvertisement),null,
        null, null, null);
    }

    @Override
    public JobAdvertisementResponse update(JobAdvertisementRequest jobAdvertisementRequest) {
        JobAdvertisement jobAdvertisement = jobAdvertisementUpdater(jobAdvertisementRequest);
        jobAdvertisementRepository.save(jobAdvertisement);
        return getJobAdvertisementResponseById(jobAdvertisement.getId());
    }

    @Override
    public void delete(Long id) {

        /*if (!jobAdvertisementRepository.existsById(id)){
            throw new NotFoundException("Job Advertisement is not found");
        }*/
        if (!isJobAdvertisementBelongToEmployer(id))
            throw new BadRequestException("You can't delete someone else's job advertisement.");

        JobAdvertisement jobAdvertisement = getJobAdvertisementById(id);
        Long jobAdvertisementId = jobAdvertisement.getId();

        JobLocation jobLocation = jobLocationService.getJobLocationByJobAdvertisementId(jobAdvertisementId);
        if (jobLocation !=null) jobLocationService.delete(jobLocation.getId());

        WorkingTime workingTime = workingTimeService.getWorkingTimeByJobAdvertisementId(jobAdvertisementId);
        if (workingTime !=null) workingTimeService.delete(workingTime.getId());

        JobTitle jobTitle = jobTitleService.getJobTitleByJobAdvertisementId(jobAdvertisementId);
        if (jobTitle !=null) jobTitleService.delete(jobTitle.getId());

        EmploymentType employmentType = employmentTypeService.getEmploymentTypeByJobAdvertisementId(jobAdvertisementId);
        if (employmentType !=null) employmentTypeService.delete(employmentType.getId());

        jobAdvertisementRepository.deleteById(id);
    }

    @Override
    public JobAdvertisement getJobAdvertisementById(Long id) {
        return jobAdvertisementRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Job Advertisement is not found"));
    }

    @Override
    public JobAdvertisementResponse getJobAdvertisementResponseById(Long id) {
        JobAdvertisement jobAdvertisement = getJobAdvertisementById(id);
        return JobAdvertisementResponse.of(jobAdvertisement,
                JobLocationResponse.of(jobLocationService.getJobLocationByJobAdvertisementId(id)),
                WorkingTimeResponse.of(workingTimeService.getWorkingTimeByJobAdvertisementId(id)),
                JobTitleResponse.of(jobTitleService.getJobTitleByJobAdvertisementId(id)),
                EmploymentTypeResponse.of(employmentTypeService.getEmploymentTypeByJobAdvertisementId(id)));
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisements() {
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findAll();
        /*checkReturnedList(jobAdvertisementList);*/
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrue() {
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findByActiveTrue();
        /*checkReturnedList(jobAdvertisementList);*/
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc() {
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateAsc();
        /*checkReturnedList(jobAdvertisementList);*/
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc() {
        List<JobAdvertisement> jobAdvertisementList =jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateDesc();
        /*checkReturnedList(jobAdvertisementList);*/
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndEmployerId(Long employerId) {
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findByActiveTrueAndEmployerId(employerId);
        /*checkReturnedList(jobAdvertisementList);*/
        return  createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByEmployerId(Long employerId){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findAllByEmployerId(employerId);
        /*checkReturnedList(jobAdvertisementList);*/
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    private List<JobAdvertisementResponse> createJobAdvertisementResponseList(List<JobAdvertisement> jobAdvertisementList){
        return jobAdvertisementList
                .stream()
                .map(jobAdvertisement -> JobAdvertisementResponse.of(jobAdvertisement,
                        JobLocationResponse.of(jobLocationService.getJobLocationByJobAdvertisementId(jobAdvertisement.getId())),
                        WorkingTimeResponse.of(workingTimeService.getWorkingTimeByJobAdvertisementId(jobAdvertisement.getId())),
                        JobTitleResponse.of(jobTitleService.getJobTitleByJobAdvertisementId(jobAdvertisement.getId())),
                        EmploymentTypeResponse.of(employmentTypeService.getEmploymentTypeByJobAdvertisementId(jobAdvertisement.getId()))))
                .collect(Collectors.toList());
    }

    private void checkReturnedList (List<JobAdvertisement> jobAdvertisementList) {
        if (jobAdvertisementList.isEmpty())
            throw new NotFoundException("Any Job Advertisement record isn't found");
    }

    private JobAdvertisement jobAdvertisementCreator(JobAdvertisementRequest jobAdvertisementRequest){
        if (jobAdvertisementRequest == null) {
            throw new NotFoundException("No job Advertisement record found to add");
        }
        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        if (jobAdvertisementRequest.getDescription()!=null)jobAdvertisement.setDescription(jobAdvertisementRequest.getDescription());
        if (jobAdvertisementRequest.getMinSalary()!=null)jobAdvertisement.setMinSalary(jobAdvertisementRequest.getMinSalary());
        if (jobAdvertisementRequest.getMaxSalary()!=null)jobAdvertisement.setMaxSalary(jobAdvertisementRequest.getMaxSalary());
        if (jobAdvertisementRequest.getNumberOfOpenJobPosition()!=null)jobAdvertisement.setNumberOfOpenJobPosition(jobAdvertisementRequest.getNumberOfOpenJobPosition());
        if (jobAdvertisementRequest.getApplicationDeadline()!=null)jobAdvertisement.setApplicationDeadline(jobAdvertisementRequest.getApplicationDeadline());
        if (jobAdvertisementRequest.getActive()!=null)jobAdvertisement.setActive(jobAdvertisementRequest.getActive());
        jobAdvertisement.setCreatedDate(LocalDate.now());
        jobAdvertisement.setEmployer(getEmployerFromAuthentication());
        return jobAdvertisement;
    }

    private JobAdvertisement jobAdvertisementUpdater(JobAdvertisementRequest jobAdvertisementRequest){
        if (jobAdvertisementRequest == null)
            throw new NotFoundException("No Job Advertisement record found to update");
        if (!isJobAdvertisementBelongToEmployer(jobAdvertisementRequest.getJobAdvertisementId()))
            throw new BadRequestException("You can't update someone else's job advert.");

        JobAdvertisement jobAdvertisement = jobAdvertisementRepository.findById(jobAdvertisementRequest.getJobAdvertisementId()).get();
        /*.orElseThrow(()-> new NotFoundException("No Job Advertisement with this Id in Repository"));*/

        if (jobAdvertisementRequest.getDescription()!=null)jobAdvertisement.setDescription(jobAdvertisementRequest.getDescription());
        if (jobAdvertisementRequest.getMinSalary()!=null)jobAdvertisement.setMinSalary(jobAdvertisementRequest.getMinSalary());
        if (jobAdvertisementRequest.getMaxSalary()!=null)jobAdvertisement.setMaxSalary(jobAdvertisementRequest.getMaxSalary());
        if (jobAdvertisementRequest.getNumberOfOpenJobPosition()!=null)jobAdvertisement.setNumberOfOpenJobPosition(jobAdvertisementRequest.getNumberOfOpenJobPosition());
        if (jobAdvertisementRequest.getApplicationDeadline()!=null)jobAdvertisement.setApplicationDeadline(jobAdvertisementRequest.getApplicationDeadline());
        if (jobAdvertisementRequest.getActive()!=null)jobAdvertisement.setActive(jobAdvertisementRequest.getActive());
        return jobAdvertisement;
    }

    private Employer getEmployerFromAuthentication (){
        User user = userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication());
        return employerService.getEmployerByUserId(user.getId());
    }

    private boolean isJobAdvertisementBelongToEmployer(Long jobAdvertisementId){
        return jobAdvertisementRepository.isJobAdvertisementBelongToEmployer(jobAdvertisementId,
                UserServiceImpl.getUsernameFromAuthentication());
    }
}
