package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.Candidate;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.UserExistException;
import com.umutdoruk.hrms.repository.CandidatesRepository;
import com.umutdoruk.hrms.service.services.CandidateService;
import com.umutdoruk.hrms.service.services.UserService;
import com.umutdoruk.hrms.utilities.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidatesRepository candidatesRepository;
    private final UserService userService;

    @Autowired
    public CandidateServiceImpl(CandidatesRepository candidatesRepository, UserService userService) {
        this.candidatesRepository = candidatesRepository;
        this.userService = userService;
    }

    @Override
    public void add(Candidate candidate) {

        if (isUserExist(candidate))
            throw  new UserExistException("User already exist");
        else if (!Validators.candidateValidator(candidate))
            throw new BadRequestException("Candidate is in incorrect format");
        else if (candidate.getUsername()==null)
            candidate.setUsername(Validators.createUsernameIfNoPresent(candidate));

        candidate.setCreatedDate(LocalDate.now());
        candidatesRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAll() {
        return candidatesRepository.findAll();
    }

    @Override
    public Candidate findByEmail(String email) {
        return candidatesRepository.findByEmail(email);
    }

    public Boolean isUserExist(Candidate candidate) {

        return userService.isEmailExist(candidate.getEmail());
    }
}
