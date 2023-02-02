package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.JobAdvertisementRequest;
import com.umutdoruk.hrms.DTO.response.*;
import com.umutdoruk.hrms.entities.*;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.JobAdvertisementRepository;
import com.umutdoruk.hrms.service.services.*;
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
    private CityService cityService;
    @Autowired
    private JobPositionService jobPositionService;
    @Autowired
    private JobTypeService jobTypeService;
    @Autowired
    private TypeOfWorkService typeOfWorkService;
    @Autowired
    private EmployerService employerService;

    @Override
    public void create(JobAdvertisementRequest jobAdvertisementRequest) {
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
        jobAdvertisement.setEmployer(employerService.getEmployerById(jobAdvertisementRequest.getEmployerId()));
        jobAdvertisementRepository.save(jobAdvertisement);
    }

    @Override
    public void update(JobAdvertisementRequest jobAdvertisementRequest) {

        if (jobAdvertisementRequest == null)
            throw new NotFoundException("No Job Advertisement record found to update");

        JobAdvertisement jobAdvertisement = jobAdvertisementRepository.findById(jobAdvertisementRequest.getJobAdvertisementId())
                .orElseThrow(()-> new NotFoundException("No Job Advertisement with this Id in Repository"));

        if (jobAdvertisementRequest.getDescription()!=null)jobAdvertisement.setDescription(jobAdvertisementRequest.getDescription());
        if (jobAdvertisementRequest.getMinSalary()!=null)jobAdvertisement.setMinSalary(jobAdvertisementRequest.getMinSalary());
        if (jobAdvertisementRequest.getMaxSalary()!=null)jobAdvertisement.setMaxSalary(jobAdvertisementRequest.getMaxSalary());
        if (jobAdvertisementRequest.getNumberOfOpenJobPosition()!=null)jobAdvertisement.setNumberOfOpenJobPosition(jobAdvertisementRequest.getNumberOfOpenJobPosition());
        if (jobAdvertisementRequest.getApplicationDeadline()!=null)jobAdvertisement.setApplicationDeadline(jobAdvertisementRequest.getApplicationDeadline());
        if (jobAdvertisementRequest.getActive()!=null)jobAdvertisement.setActive(jobAdvertisementRequest.getActive());
        jobAdvertisementRepository.save(jobAdvertisement);
    }

    @Override
    public void delete(Long id) {
        if (jobAdvertisementRepository.existsById(id)) jobAdvertisementRepository.deleteById(id);
        else throw new NotFoundException("Job Advertisement is not found");
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
                CityResponse.of(cityService.getCityByJobAdvertisementId(id)),
                JobPositionResponse.of(jobPositionService.getJobPositionByJobAdvertisementId(id)),
                JobTypeResponse.of(jobTypeService.getJobTypeByJobAdvertisementId(id)),
                TypeOfWorkResponse.of(typeOfWorkService.getTypeOfWorkByJobAdvertisementId(id)));
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisements() {
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findAll();
        checkReturnedList(jobAdvertisementList);
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrue() {
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findByActiveTrue();
        checkReturnedList(jobAdvertisementList);
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc() {
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateAsc();
        checkReturnedList(jobAdvertisementList);
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc() {
        List<JobAdvertisement> jobAdvertisementList =jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateDesc();
        checkReturnedList(jobAdvertisementList);
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndEmployerId(Long employerId) {
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findByActiveTrueAndEmployerId(employerId);
        checkReturnedList(jobAdvertisementList);
        return  createJobAdvertisementResponseList(jobAdvertisementList);
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByEmployerId(Long employerId){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementRepository.findAllByEmployerId(employerId);
        checkReturnedList(jobAdvertisementList);
        return createJobAdvertisementResponseList(jobAdvertisementList);
    }

    private List<JobAdvertisementResponse> createJobAdvertisementResponseList(List<JobAdvertisement> jobAdvertisementList){
        return jobAdvertisementList
                .stream()
                .map(jobAdvertisement -> JobAdvertisementResponse.of(jobAdvertisement,
                        CityResponse.of(cityService.getCityByJobAdvertisementId(jobAdvertisement.getId())),
                        JobPositionResponse.of(jobPositionService.getJobPositionByJobAdvertisementId(jobAdvertisement.getId())),
                        JobTypeResponse.of(jobTypeService.getJobTypeByJobAdvertisementId(jobAdvertisement.getId())),
                        TypeOfWorkResponse.of(typeOfWorkService.getTypeOfWorkByJobAdvertisementId(jobAdvertisement.getId()))))
                .collect(Collectors.toList());
    }

    private void checkReturnedList (List<JobAdvertisement> jobAdvertisementList) {
        if (jobAdvertisementList.isEmpty())
            throw new NotFoundException("Any Job Advertisement record isn't found");
    }
}
