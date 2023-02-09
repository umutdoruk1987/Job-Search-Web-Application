package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.CandidateRequest;
import com.umutdoruk.jobSearch.dto.response.CandidateResponse;
import com.umutdoruk.jobSearch.entities.Candidate;

import java.util.List;

public interface CandidateService {
    CandidateResponse create(CandidateRequest candidateRequest);
    CandidateResponse update(CandidateRequest candidateRequest);
    void delete ();
    Candidate getCandidateById(Long id);
    Candidate getCandidateByUserId(Long userId);
    CandidateResponse getCandidateResponseById(Long id);
    List<CandidateResponse> getAllCandidateResponses();
}
