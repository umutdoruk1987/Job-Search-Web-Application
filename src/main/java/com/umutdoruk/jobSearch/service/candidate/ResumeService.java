package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.ResumeRequest;
import com.umutdoruk.jobSearch.dto.candidate.ResumeResponse;
import com.umutdoruk.jobSearch.entities.candidate.Resume;

import java.util.List;

public interface ResumeService {

    ResumeResponse create(ResumeRequest resumeRequest);
    ResumeResponse update(ResumeRequest resumeRequest);
    void delete(/*Long id*/);
    Resume getResumeById(Long id);
    Resume getResumeByCandidateId (Long candidateId);
    ResumeResponse getResumeResponseByCandidateId (Long candidateId);
    ResumeResponse getResumeResponseById(Long id);
    List<ResumeResponse> getAllResumeResponses();
    Resume getResumeFromAuthentication();
}
