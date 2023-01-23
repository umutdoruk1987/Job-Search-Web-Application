package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.Candidate;

import java.util.List;

public interface CandidateService {

    void add(Candidate candidate);
    List<Candidate> getAll();
    Candidate findByEmail(String email);
    Candidate findById(Long id);
    void update(Candidate candidate);
    void delete(Long id);
    Boolean isUserExist(Candidate candidate);

}
