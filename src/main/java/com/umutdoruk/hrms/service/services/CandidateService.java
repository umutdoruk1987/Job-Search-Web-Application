package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.CandidateRequest;
import com.umutdoruk.hrms.DTO.response.CandidateResponse;

import java.util.List;

public interface CandidateService {

    void add(CandidateRequest candidateRequest);
    List<CandidateResponse> getAll();
    CandidateResponse findByEmail(String email);
    CandidateResponse findById(Long id);
    void update(CandidateRequest candidateRequest);
    void delete(Long id);
    Boolean isUserExist(CandidateRequest candidateRequest);

}
