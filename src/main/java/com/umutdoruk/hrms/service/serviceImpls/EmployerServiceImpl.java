package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.EmployerRequest;
import com.umutdoruk.hrms.DTO.response.EmployerResponse;
import com.umutdoruk.hrms.DTO.response.JobAdvertisementResponse;
import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.EmployerRepository;
import com.umutdoruk.hrms.service.services.EmployerService;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import com.umutdoruk.hrms.service.services.UserService;
import com.umutdoruk.hrms.utilities.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;
    private final UserService userService;
    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository,
                               UserService userService,
                               JobAdvertisementService jobAdvertisementService) {
        this.employerRepository = employerRepository;
        this.userService = userService;
        this.jobAdvertisementService=jobAdvertisementService;
    }

    @Override
    public void create(EmployerRequest employerRequest) {

        if (!Validators.employerValidator(employerRequest))
            throw new BadRequestException("Employer must be in the correct format and complete");

        Employer employer = new Employer();
        employer.setCompanyName(employerRequest.getCompanyName());
        employer.setCompanyTelephoneNumber(employerRequest.getCompanyTelephoneNumber());
        employer.setWebsite(employerRequest.getWebsite());
        employer.setUser(userService.getUserById(employerRequest.getUserId()));

        employerRepository.save(employer);
    }

    @Override
    public void update(EmployerRequest employerRequest, Long id) {

        if (employerRequest == null)
            throw new NotFoundException("No Employer record found to update");

        Employer employer = getEmployerById(id);
        employer.setCompanyName(employerRequest.getCompanyName());
        employer.setWebsite(employerRequest.getWebsite());
        employer.setCompanyTelephoneNumber(employerRequest.getCompanyTelephoneNumber());
        employer.setUser(userService.getUserById(employerRequest.getUserId()));

        employerRepository.save(employer);
    }

    @Override
    public void delete(Long id) {

        if (!(employerRepository.existsById(id)))
            throw new NotFoundException("Employer is not found");
        employerRepository.deleteById(id);
    }

    @Override
    public Employer getEmployerById(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Employer is not found"));
    }

    @Override
    public EmployerResponse getEmployerResponseById(Long id) {
        return EmployerResponse.of(getEmployerById(id),createJobAdvertisementResponseListByEmployerId(id));
    }

    @Override
    public EmployerResponse getEmployerResponseByEmail(String email) {
        Employer employer = employerRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("Employer is not found"));

        return EmployerResponse.of(employer,createJobAdvertisementResponseListByEmployerId(employer.getId()));
    }

    @Override
    public List<EmployerResponse> getAllEmployerResponses() {

        List<EmployerResponse> employerResponseList = new ArrayList<>();

        for (Employer employer : employerRepository.findAll()){
            List<JobAdvertisementResponse> jobAdvertisementResponseList =
                    jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employer.getId());
            employerResponseList.add(EmployerResponse.of(employer,jobAdvertisementResponseList));
        }

        if (employerResponseList.size()==0){
            throw new NotFoundException("Any Employer record isn't found");
        }

        return employerResponseList;
    }

    private List<JobAdvertisementResponse> createJobAdvertisementResponseListByEmployerId (Long employerId){

        return jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employerId);
    }

}
