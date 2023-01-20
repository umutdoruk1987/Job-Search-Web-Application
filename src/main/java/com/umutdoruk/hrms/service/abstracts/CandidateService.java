package com.umutdoruk.hrms.service.abstracts;

import com.umutdoruk.hrms.entities.concretes.Candidate;
import java.util.List;

public interface CandidateService {

    void add(Candidate candidate);
    List<Candidate> getAll();
    Candidate findByEmail(String email);

}
