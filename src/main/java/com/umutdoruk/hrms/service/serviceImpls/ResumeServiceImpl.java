package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.concretes.Resume;
import com.umutdoruk.hrms.repository.ResumeRepository;
import com.umutdoruk.hrms.service.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public void add(Resume resume) {
        resumeRepository.save(resume);
    }

    @Override
    public Resume findByResumeId(int id) {
        return resumeRepository.findById(id).get();
    }

    @Override
    public List<Resume> getAll() {
        return resumeRepository.findAll();
    }

    @Override
    public void update(Resume resume) {
        resumeRepository.save(resume);
    }

}