package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.exception.BadRequestException;
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
    public void add(Employer employer) {

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
    public List<Employer> getAll() {
        return employerRepository.findAll();
    }

    @Override
    public Employer findByEmail(String email) {
        return employerRepository.findByEmail(email);
    }

    public Boolean isUserExist(Employer employer) {

        return userService.isEmailExist(employer.getEmail());
    }

}
