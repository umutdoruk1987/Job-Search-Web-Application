package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.JobAdvertisementRequest;
import com.umutdoruk.hrms.DTO.response.JobAdvertisementResponse;
import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.entities.JobAdvertisement;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.JobAdvertisementRepository;
import com.umutdoruk.hrms.service.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final CityService cityService;
    private final JobPositionService jobPositionService;
    private final JobTypeService jobTypeService;
    private final TypeOfWorkService typeOfWorkService;
    private final EmployerService employerService;

    @Autowired
    public JobAdvertisementServiceImpl(JobAdvertisementRepository jobAdvertisementRepository,
                                       CityService cityService,
                                       JobPositionService jobPositionService,
                                       JobTypeService jobTypeService,
                                       TypeOfWorkService typeOfWorkService,
                                       EmployerService employerService) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
        this.cityService = cityService;
        this.jobPositionService = jobPositionService;
        this.jobTypeService = jobTypeService;
        this.typeOfWorkService = typeOfWorkService;
        this.employerService = employerService;
    }

    @Override
    public void create(JobAdvertisementRequest jobAdvertisementRequest) {
        if (jobAdvertisementRequest == null) {
            throw new NotFoundException("No job Advertisement record found to add");
        }

        JobAdvertisement jobAdvertisement = new JobAdvertisement();
        jobAdvertisement.setDescription(jobAdvertisementRequest.getDescription());
        jobAdvertisement.setMinSalary(jobAdvertisementRequest.getMinSalary());
        jobAdvertisement.setMaxSalary(jobAdvertisementRequest.getMaxSalary());
        jobAdvertisement.setNumberOfOpenJobPosition(jobAdvertisementRequest.getNumberOfOpenJobPosition());
        jobAdvertisement.setApplicationDeadline(jobAdvertisementRequest.getApplicationDeadline());
        jobAdvertisement.setCreatedDate(LocalDate.now());
        jobAdvertisement.setActive(jobAdvertisementRequest.isActive());
        jobAdvertisement.setCity(cityService.getCityById(jobAdvertisementRequest.getCityId()));
        jobAdvertisement.setJobPosition(jobPositionService.getJobPositionById(jobAdvertisementRequest.getJobPositionId()));
        jobAdvertisement.setJobType(jobTypeService.getJobTypeById(jobAdvertisementRequest.getJobTypeId()));
        jobAdvertisement.setTypeOfWork(typeOfWorkService.getTypeOfWorkById(jobAdvertisementRequest.getTypeOfWorkId()));
        jobAdvertisement.setEmployer(employerService.getEmployerById(jobAdvertisementRequest.getEmployerId()));

        jobAdvertisementRepository.save(jobAdvertisement);
    }

    @Override
    public void update(JobAdvertisementRequest jobAdvertisementRequest, Long jobAdvertisementId) {

        JobAdvertisement jobAdvertisement = jobAdvertisementRepository.findById(jobAdvertisementId)
                .orElseThrow(()-> new NotFoundException("No Job Advertisement with this Id in Repository"));

        if (jobAdvertisementRequest == null)
            throw new NotFoundException("No Job Advertisement record found to update");

        jobAdvertisement.setDescription(jobAdvertisementRequest.getDescription());
        jobAdvertisement.setMinSalary(jobAdvertisementRequest.getMinSalary());
        jobAdvertisement.setMaxSalary(jobAdvertisementRequest.getMaxSalary());
        jobAdvertisement.setNumberOfOpenJobPosition(jobAdvertisementRequest.getNumberOfOpenJobPosition());
        jobAdvertisement.setApplicationDeadline(jobAdvertisementRequest.getApplicationDeadline());
        jobAdvertisement.setCreatedDate(LocalDate.now());
        jobAdvertisement.setActive(jobAdvertisementRequest.isActive());
        jobAdvertisement.setCity(cityService.getCityById(jobAdvertisementRequest.getCityId()));
        jobAdvertisement.setJobPosition(jobPositionService.getJobPositionById(jobAdvertisementRequest.getJobPositionId()));
        jobAdvertisement.setJobType(jobTypeService.getJobTypeById(jobAdvertisementRequest.getJobTypeId()));
        jobAdvertisement.setTypeOfWork(typeOfWorkService.getTypeOfWorkById(jobAdvertisementRequest.getTypeOfWorkId()));
        jobAdvertisement.setEmployer(employerService.getEmployerById(jobAdvertisementRequest.getEmployerId()));

        jobAdvertisementRepository.save(jobAdvertisement);
    }

    @Override
    public void delete(Long id) {
        if (!(jobAdvertisementRepository.existsById(id)))
            throw new NotFoundException("Job Advertisement is not found");

        jobAdvertisementRepository.deleteById(id);
    }

    @Override
    public JobAdvertisement getJobAdvertisementById(Long id) {
        return jobAdvertisementRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Job Advertisement is not found"));
    }

    @Override
    public JobAdvertisementResponse getJobAdvertisementResponseById(Long id) {
        return JobAdvertisementResponse.of(getJobAdvertisementById(id));
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisements() {
        if (jobAdvertisementRepository.findAll().isEmpty())
            throw new NotFoundException("Any Job Advertisement record isn't found");

        return jobAdvertisementRepository.findAll()
                .stream()
                .map(jobAdvertisement -> JobAdvertisementResponse.of(jobAdvertisement))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrue() {
        if (jobAdvertisementRepository.findByActiveTrue().isEmpty())
            throw new NotFoundException("Any Job Advertisement record isn't found");

        return jobAdvertisementRepository.findByActiveTrue()
                .stream()
                .map(jobAdvertisement -> JobAdvertisementResponse.of(jobAdvertisement))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc() {
        if (jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateAsc().isEmpty())
            throw new NotFoundException("Any Job Advertisement record isn't found");

        return jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateAsc()
                .stream()
                .map(jobAdvertisement -> JobAdvertisementResponse.of(jobAdvertisement))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc() {
        if (jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateDesc().isEmpty())
            throw new NotFoundException("Any Job Advertisement record isn't found");

        return jobAdvertisementRepository.findByActiveTrueOrderByCreatedDateDesc()
                .stream()
                .map(jobAdvertisement -> JobAdvertisementResponse.of(jobAdvertisement))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByActiveTrueAndEmployerId(Long employerId) {

        if (jobAdvertisementRepository.findByActiveTrueAndEmployerId(employerId).isEmpty())
            throw new NotFoundException("Any Job Advertisement record isn't found");

        return jobAdvertisementRepository.findByActiveTrueAndEmployerId(employerId)
                .stream()
                .map(jobAdvertisement -> JobAdvertisementResponse.of(jobAdvertisement))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobAdvertisementResponse> getAllJobAdvertisementsByEmployerId(@PathVariable Long employerId){

        Employer employer = employerService.getEmployerById(employerId);

        List<JobAdvertisementResponse> jobAdvertisementResponseList =
                employer.getJobAdvertisements()
                .stream()
                .map(jobAdvertisement -> JobAdvertisementResponse.of(jobAdvertisement))
                .collect(Collectors.toList());

        if (jobAdvertisementResponseList.size()==0)
            throw new NotFoundException("Any Job Advertisement record isn't found");

        return  jobAdvertisementResponseList;
    }
}
