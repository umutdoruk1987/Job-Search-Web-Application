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

    /*@Autowired
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
    }*/

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
        jobAdvertisement.setActive(jobAdvertisementRequest.getActive());
        if (jobAdvertisementRequest.getCityId() != null )jobAdvertisement.setCity(cityService.getCityById(jobAdvertisementRequest.getCityId()));
        if (jobAdvertisementRequest.getJobPositionId()!= null)jobAdvertisement.setJobPosition(jobPositionService.getJobPositionById(jobAdvertisementRequest.getJobPositionId()));
        if (jobAdvertisementRequest.getJobTypeId()!=null)jobAdvertisement.setJobType(jobTypeService.getJobTypeById(jobAdvertisementRequest.getJobTypeId()));
        if (jobAdvertisementRequest.getTypeOfWorkId()!=null)jobAdvertisement.setTypeOfWork(typeOfWorkService.getTypeOfWorkById(jobAdvertisementRequest.getTypeOfWorkId()));
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
        else if (jobAdvertisementRequest.getMinSalary()!=null)jobAdvertisement.setMinSalary(jobAdvertisementRequest.getMinSalary());
        else if (jobAdvertisementRequest.getMaxSalary()!=null)jobAdvertisement.setMaxSalary(jobAdvertisementRequest.getMaxSalary());
        else if (jobAdvertisementRequest.getNumberOfOpenJobPosition()!=null)jobAdvertisement.setNumberOfOpenJobPosition(jobAdvertisementRequest.getNumberOfOpenJobPosition());
        else if (jobAdvertisementRequest.getApplicationDeadline()!=null)jobAdvertisement.setApplicationDeadline(jobAdvertisementRequest.getApplicationDeadline());
        /*jobAdvertisement.setCreatedDate(LocalDate.now());*/
        else if (jobAdvertisementRequest.getActive()!=null)jobAdvertisement.setActive(jobAdvertisementRequest.getActive());
        else if (jobAdvertisementRequest.getCityId()!=null)jobAdvertisement.setCity(cityService.getCityById(jobAdvertisementRequest.getCityId()));
        else if (jobAdvertisementRequest.getJobPositionId()!=null)jobAdvertisement.setJobPosition(jobPositionService.getJobPositionById(jobAdvertisementRequest.getJobPositionId()));
        else if (jobAdvertisementRequest.getJobTypeId()!=null)jobAdvertisement.setJobType(jobTypeService.getJobTypeById(jobAdvertisementRequest.getJobTypeId()));
        else if (jobAdvertisementRequest.getTypeOfWorkId()!=null)jobAdvertisement.setTypeOfWork(typeOfWorkService.getTypeOfWorkById(jobAdvertisementRequest.getTypeOfWorkId()));
        /*jobAdvertisement.setEmployer(employerService.getEmployerById(jobAdvertisementRequest.getEmployerId()));
*/
        jobAdvertisementRepository.save(jobAdvertisement);
    }

    @Override
    public void delete(Long id) {
        if (jobAdvertisementRepository.existsById(id))
        jobAdvertisementRepository.deleteById(id);
        else throw new NotFoundException("Job Advertisement is not found");
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
