package com.umutdoruk.hrms.service.concretes;

import com.umutdoruk.hrms.entities.concretes.Candidate;
import com.umutdoruk.hrms.repository.CandidateRepository;
import com.umutdoruk.hrms.service.abstracts.CandidateService;
import com.umutdoruk.hrms.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final UserService userService;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, UserService userService) {
        this.candidateRepository = candidateRepository;
        this.userService = userService;
    }

    @Override
    public void add(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }
}