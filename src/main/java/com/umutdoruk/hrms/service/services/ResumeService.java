package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.ResumeRequest;
import com.umutdoruk.hrms.DTO.response.ResumeResponse;
import com.umutdoruk.hrms.entities.Resume;

import java.util.List;

public interface ResumeService {

    void add(ResumeRequest resumeRequest);
    ResumeResponse findById(Long id);
    Resume getById(Long id);
    List<Resume> getAll();
    void update(ResumeRequest resumeRequest, Long resumeId);
    void delete(Long id);
}
