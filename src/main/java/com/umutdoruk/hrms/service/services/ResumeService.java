package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.ResumeRequest;
import com.umutdoruk.hrms.DTO.response.ResumeResponse;
import com.umutdoruk.hrms.entities.Resume;

import java.util.List;

public interface ResumeService {

    void add(ResumeRequest resumeRequest);
    ResumeResponse findResumeById(Long id);
    Resume getResumeById(Long id);
    List<ResumeResponse> getAll();
    void update(ResumeRequest resumeRequest);
    void delete(Long id);
}
