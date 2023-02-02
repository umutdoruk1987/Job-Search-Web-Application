package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.EmployerRequest;
import com.umutdoruk.hrms.DTO.response.EmployerResponse;
import com.umutdoruk.hrms.DTO.response.JobAdvertisementResponse;
import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.EmployerRepository;
import com.umutdoruk.hrms.service.services.CandidateService;
import com.umutdoruk.hrms.service.services.EmployerService;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import com.umutdoruk.hrms.service.services.UserService;
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
    public void create(EmployerRequest employerRequest) {

        employerRepository.save(employerCreator(employerRequest));
    }

    @Override
    public void update(EmployerRequest employerRequest) {

        employerRepository.save(employerUpdater(employerRequest));
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

    private void employerCreateValidator(EmployerRequest employerRequest){
        if (employerRequest.getWebsite()!=null && !employerRequest.getWebsite().startsWith("www."))
            throw new BadRequestException("Website name has to start with www.");
        if (employerRequest.getWebsite()==null)
            throw new BadRequestException("Website field have to be filled");
        if (employerRequest.getCompanyName()==null)
            throw new BadRequestException("Company name field have to be filled");
        if (employerRequest.getCompanyTelephoneNumber()!=null && employerRequest.getCompanyTelephoneNumber().length()!=11)
            throw new BadRequestException("Company telephone number can only have 11 digits.");
        if (getEmployerByUserId(employerRequest.getUserId())!=null)
            throw new BadRequestException("A user cannot have more than one account");
        if (candidateService.getCandidateByUserId(employerRequest.getUserId())!=null)
            throw new BadRequestException("A user cannot have more than one role");

    }

    private Employer employerCreator(EmployerRequest employerRequest) {
        employerCreateValidator(employerRequest);

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
        if (employerRequest.getCompanyTelephoneNumber()!=null && employerRequest.getCompanyTelephoneNumber().length()!=11)
            throw new BadRequestException("Company telephone number can only have 11 digits.");
        if (employerRequest.getWebsite()!=null && !employerRequest.getWebsite().startsWith("www."))
            throw new BadRequestException("Website name has to start with www.");
    }

    private Employer employerUpdater(EmployerRequest employerRequest){
        employerUpdateValidator(employerRequest);
        Employer employer = getEmployerById(employerRequest.getId());

        if (employerRequest.getCompanyName()!=null)employer.setCompanyName(employerRequest.getCompanyName());
        if (employerRequest.getWebsite()!=null)employer.setWebsite(employerRequest.getWebsite());
        if (employerRequest.getCompanyTelephoneNumber()!=null)employer.setCompanyTelephoneNumber(employerRequest.getCompanyTelephoneNumber());
        /*employer.setUser(userService.getUserById(employerRequest.getUserId()));*/

        return employer;
    }
}
