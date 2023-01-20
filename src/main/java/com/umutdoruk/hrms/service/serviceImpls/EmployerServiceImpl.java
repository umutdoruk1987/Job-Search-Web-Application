package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.repository.EmployerRepository;
import com.umutdoruk.hrms.service.services.EmployerService;
import com.umutdoruk.hrms.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
