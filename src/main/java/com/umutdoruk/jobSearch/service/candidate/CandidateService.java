package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.CandidateRequest;
import com.umutdoruk.jobSearch.dto.candidate.CandidateResponse;
import com.umutdoruk.jobSearch.entities.candidate.Candidate;

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
