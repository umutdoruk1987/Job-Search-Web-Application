package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.ResumeRequest;
import com.umutdoruk.hrms.DTO.response.ResumeResponse;
import com.umutdoruk.hrms.entities.Resume;

import java.util.List;

public interface ResumeService {

    void create(ResumeRequest resumeRequest);
    void update(ResumeRequest resumeRequest);
    void delete(Long id);
    Resume getResumeById(Long id);
    Resume getResumeByCandidateId (Long candidateId);
    ResumeResponse getResumeResponseById(Long id);
    List<ResumeResponse> getAllResumeResponses();
}
