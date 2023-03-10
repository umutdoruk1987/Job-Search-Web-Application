package com.umutdoruk.jobSearch.service.employer;

import com.umutdoruk.jobSearch.dto.employer.EmployerRequest;
import com.umutdoruk.jobSearch.dto.employer.EmployerResponse;
import com.umutdoruk.jobSearch.dto.employer.JobAdvertisementResponse;
import com.umutdoruk.jobSearch.entities.employer.Employer;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.employer.EmployerRepository;
import com.umutdoruk.jobSearch.service.user.UserServiceImpl;
import com.umutdoruk.jobSearch.service.candidate.CandidateService;
import com.umutdoruk.jobSearch.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private JobAdvertisementService jobAdvertisementService;

    /*@Autowired*/
    /*public EmployerServiceImpl(EmployerRepository employerRepository,
                               UserService userService,
                               JobAdvertisementService jobAdvertisementService) {
        this.employerRepository = employerRepository;
        this.userService = userService;
        this.jobAdvertisementService=jobAdvertisementService;
    }*/

    @Override
    public EmployerResponse create(EmployerRequest employerRequest) {
        Employer employer = employerRepository.save(employerCreator(employerRequest));
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employer.getId());
        return EmployerResponse.of(employer,jobAdvertisementResponseList);
    }

    @Override
    public EmployerResponse update(EmployerRequest employerRequest) {
        Employer employer = employerRepository.save(employerUpdater(employerRequest));
        List<JobAdvertisementResponse> jobAdvertisementList = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employer.getId());
        return EmployerResponse.of(employer,jobAdvertisementList);
    }

    @Override
    public void delete() {
        Employer employer = employerRepository
                .findEmployerByUserId(userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication()).getId());
        List<JobAdvertisementResponse> jobAdvertisementList = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employer.getId());

        /*if (jobAdvertisementList.size()!=0)*/
        jobAdvertisementList.forEach(jobAdvertisementResponse -> jobAdvertisementService.delete(jobAdvertisementResponse.getJobAdvertisementId()));

        employerRepository.deleteById(employer.getId());
    }

    @Override
    public Employer getEmployerById(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Employer is not found"));
    }

    @Override
    public Employer getEmployerByUserId(Long userId) {
        return employerRepository.findEmployerByUserId(userId);
    }

    @Override
    public EmployerResponse getEmployerResponseById(Long id) {
        return EmployerResponse.of(getEmployerById(id),createJobAdvertisementResponseListByEmployerId(id));
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

    private Employer employerCreator(EmployerRequest employerRequest) {
        //employerCreateValidator(employerRequest);

        Employer employer = new Employer();
        employer.setCompanyName(employerRequest.getCompanyName());
        employer.setCompanyTelephoneNumber(employerRequest.getCompanyTelephoneNumber());
        employer.setWebsite(employerRequest.getWebsite());
        employer.setUser(userService.getUserById(employerRequest.getUserId()));

        return employer;
    }

    private void employerUpdateValidator(EmployerRequest employerRequest){
        if (employerRequest == null)
            throw new NotFoundException("No Employer record found to update");
        if (employerRequest.getCompanyName()==null)
            throw new BadRequestException("Company name field have to be filled");
        if (employerRequest.getCompanyTelephoneNumber()==null )
            throw new BadRequestException("Company telephone number field have to be filled");
        if (employerRequest.getCompanyTelephoneNumber().length()!=11)
            throw new BadRequestException("Company telephone number can only have 11 digits.");
        if (employerRequest.getWebsite()==null)
            throw new BadRequestException("Website field have to be filled");
        if (!employerRequest.getWebsite().startsWith("www."))
            throw new BadRequestException("Website name has to start with www.");
        if (getEmployerByUserId(employerRequest.getUserId())!=null)
            throw new BadRequestException("A user cannot have more than one account");
        if (candidateService.getCandidateByUserId(employerRequest.getUserId())!=null)
            throw new BadRequestException("A user cannot have more than one role");
    }

    private Employer employerUpdater(EmployerRequest employerRequest){
        employerUpdateValidator(employerRequest);

        employerRequest.setUserId(userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication()).getId());
        Employer employer = getEmployerByUserId(employerRequest.getUserId());

        /*if (employerRequest.getCompanyName()!=null)*/employer.setCompanyName(employerRequest.getCompanyName());
        /*if (employerRequest.getWebsite()!=null)*/employer.setWebsite(employerRequest.getWebsite());
        /*if (employerRequest.getCompanyTelephoneNumber()!=null)*/employer.setCompanyTelephoneNumber(employerRequest.getCompanyTelephoneNumber());

        return employer;
    }

    /*private void employerCreateValidator(EmployerRequest employerRequest){

    }*/
}
