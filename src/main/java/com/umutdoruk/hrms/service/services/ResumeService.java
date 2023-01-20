package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.Resume;

import java.util.List;

public interface ResumeService {

    void add(Resume resume);
    Resume findByResumeId(int id);
    List<Resume> getAll();
    void update(Resume resume);
}
