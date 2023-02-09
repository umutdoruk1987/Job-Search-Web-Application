package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.ResumeRequest;
import com.umutdoruk.jobSearch.dto.response.ResumeResponse;
import com.umutdoruk.jobSearch.entities.Resume;

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
