package com.umutdoruk.jobSearch.service.serviceImpls;

import com.umutdoruk.jobSearch.dto.request.EmploymentTypeRequest;
import com.umutdoruk.jobSearch.dto.response.EmploymentTypeResponse;
import com.umutdoruk.jobSearch.entities.Employer;
import com.umutdoruk.jobSearch.entities.EmploymentType;
import com.umutdoruk.jobSearch.entities.User;
import com.umutdoruk.jobSearch.enums.EmploymentTypeConstants;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.EmploymentTypeRepository;
import com.umutdoruk.jobSearch.service.services.EmployerService;
import com.umutdoruk.jobSearch.service.services.JobAdvertisementService;
import com.umutdoruk.jobSearch.service.services.EmploymentTypeService;
import com.umutdoruk.jobSearch.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmploymentTypeServiceImpl implements EmploymentTypeService {

    private final EmploymentTypeRepository employmentTypeRepository;
    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    private EmployerService employerService;
    @Autowired
    private UserService userService;

    @Autowired
    public EmploymentTypeServiceImpl(EmploymentTypeRepository employmentTypeRepository, JobAdvertisementService jobAdvertisementService) {
        this.employmentTypeRepository = employmentTypeRepository;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @Override
    public EmploymentTypeResponse create(EmploymentTypeRequest employmentTypeRequest) {

        if (employmentTypeRequest == null)
            throw new NotFoundException("No Employment Type record found to add");
        if (getEmploymentTypeByJobAdvertisementId(employmentTypeRequest.getJobAdvertisementId())!= null)
            throw new BadRequestException("You have already created a Employment Type. You can update it.");

        EmploymentType employmentType = new EmploymentType();
        employmentType.setTypeName(EmploymentTypeConstants.findByName(employmentTypeRequest.getName()));
        employmentType.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(employmentTypeRequest.getJobAdvertisementId()));

        return EmploymentTypeResponse.of(employmentTypeRepository.save(employmentType));
    }

    @Override
    public EmploymentTypeResponse update(EmploymentTypeRequest employmentTypeRequest) {

        if (employmentTypeRequest == null)
            throw new NotFoundException("No Employment Type record found to update");
        if (!isEmploymentTypeBelongedToJobAdvertisement(employmentTypeRequest.getEmploymentTypeId()))
            throw new BadRequestException("You have no such Employment Type");

        EmploymentType employmentType = getEmploymentTypeById(employmentTypeRequest.getEmploymentTypeId());
        employmentType.setTypeName(EmploymentTypeConstants.findByName(employmentTypeRequest.getName()));
        /*typeOfWork.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(typeOfWorkRequest.getJobAdvertisementId()));*/
        return EmploymentTypeResponse.of(employmentTypeRepository.save(employmentType));
    }

    @Override
    public void delete(Long id) {
        /*if (!(typeOfWorkRepository.existsById(id)))
            throw new NotFoundException("Type of Work is not found");*/
        if (!isEmploymentTypeBelongedToJobAdvertisement(id)) throw new BadRequestException("You have no such Employment Type");
        employmentTypeRepository.deleteById(id);
    }

    @Override
    public EmploymentType getEmploymentTypeById(Long id) {
        return employmentTypeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Employment Type is not found"));
    }

    @Override
    public EmploymentType getEmploymentTypeByJobAdvertisementId(Long id) {
        return employmentTypeRepository.findByJobAdvertisementId(id);
    }

    @Override
    public EmploymentTypeResponse getEmploymentTypeResponseById(Long id) {
        return EmploymentTypeResponse.of(getEmploymentTypeById(id));
    }

    @Override
    public List<EmploymentTypeResponse> getAllEmploymentTypeResponses() {
        if (employmentTypeRepository.findAll().isEmpty())
            throw new NotFoundException("Any Employment Type record isn't found");

        return employmentTypeRepository.findAll()
                .stream()
                .map(employmentType -> EmploymentTypeResponse.of(employmentType))
                .collect(Collectors.toList());
    }

    private boolean isEmploymentTypeBelongedToJobAdvertisement(Long employmentTypeId){
        User user = userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication());
        Employer employer = employerService.getEmployerByUserId(user.getId());
        long count = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employer.getId())
                .stream()
                .filter(jobAdvertisementResponse -> jobAdvertisementResponse.getEmploymentTypeResponse().getEmploymentTypeId().equals(employmentTypeId))
                .count();
        return count==1;
    }
}