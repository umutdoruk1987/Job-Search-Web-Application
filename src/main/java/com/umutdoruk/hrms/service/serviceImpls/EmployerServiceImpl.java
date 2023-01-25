package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.EmployerRequest;
import com.umutdoruk.hrms.DTO.response.EmployerResponse;
import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.exception.UserExistException;
import com.umutdoruk.hrms.repository.EmployerRepository;
import com.umutdoruk.hrms.service.services.EmployerService;
import com.umutdoruk.hrms.service.services.UserService;
import com.umutdoruk.hrms.utilities.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository employerRepository;
    private final UserService userService;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, UserService userService) {
        this.employerRepository = employerRepository;
        this.userService = userService;

    }

    @Override
    public void add(EmployerRequest employerRequest) {

        if (isUserExist(employer))
            throw  new UserExistException("User already exist");
        else if (!Validators.employerValidator(employer))
            throw new BadRequestException("Employer is in incorrect format");
        else if (employer.getUsername()==null)
            employer.setUsername(Validators.createUsernameIfNoPresent(employer));

        employer.setCreatedDate(LocalDate.now());
        employerRepository.save(employer);
    }

    @Override
    public List<EmployerResponse> getAll() {
        if (employerRepository.findAll().isEmpty())
            throw new NotFoundException("Employer is not found");
        return employerRepository.findAll();
    }

    @Override
    public EmployerResponse findByEmail(String email) {
        return employerRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("Employer is not found"));
    }

    @Override
    public EmployerResponse findById(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Employer is not found"));
    }

    @Override
    public void update(EmployerRequest employerRequest) {
        Employer employerForUpdate = employerRepository.findById(employer.getId())
                .orElseThrow(()-> new NotFoundException("Employer is not found"));

        employerForUpdate.setActive(employer.isActive());
        employerForUpdate.setCompanyName(employer.getCompanyName());
        employerForUpdate.setUsername(employer.getUsername());
        employerForUpdate.setWebsite(employer.getWebsite());
        employerForUpdate.setCompanyTelephoneNumber(employer.getCompanyTelephoneNumber());
        employerForUpdate.setPassword(employer.getPassword());
        employerForUpdate.setConfirmPassword(employer.getConfirmPassword());

        employerRepository.save(employerForUpdate);
    }

    @Override
    public void delete(Long id) {

        if (!(employerRepository.existsById(id)))
            throw new NotFoundException("Employer is not found");
        employerRepository.deleteById(id);
    }

    @Override
    public Boolean isUserExist(EmployerRequest employerRequest) {

        return userService.isEmailExist(employer.getEmail());
    }

}
