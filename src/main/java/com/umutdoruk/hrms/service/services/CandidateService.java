package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.CandidateRequest;
import com.umutdoruk.hrms.DTO.response.CandidateResponse;
import com.umutdoruk.hrms.entities.Candidate;

import java.util.List;

public interface CandidateService {

    void create(CandidateRequest candidateRequest);
    void update(CandidateRequest candidateRequest);
    Candidate getCandidateById(Long id);
    CandidateResponse getCandidateResponseById(Long id);
    List<CandidateResponse> getAllCandidateResponses();



}
