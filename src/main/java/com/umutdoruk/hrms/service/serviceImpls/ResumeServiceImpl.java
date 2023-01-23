package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.Resume;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.ResumeRepository;
import com.umutdoruk.hrms.service.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if (resume == null) {
            throw new NotFoundException("No Resume record found to add");
        }
        resume.setCreateDate(LocalDate.now());
        resumeRepository.save(resume);
    }

    @Override
    public Resume findResumeById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Resume is not found"));
    }

    @Override
    public List<Resume> getAll() {
        if (resumeRepository.findAll().isEmpty())
            throw new NotFoundException("Resume is not found");
        return resumeRepository.findAll();
    }

    @Override
    public void update(Resume resume) {
        Resume resumeForUpdate = resumeRepository.findById(resume.getId())
                .orElseThrow(()-> new NotFoundException("Resume is not found"));

        resumeForUpdate.setActive(resume.isActive());
        resumeForUpdate.setEducation(resume.getEducation());
        resumeForUpdate.setCoverLetter(resume.getCoverLetter());
        resumeForUpdate.setWorkExperience(resume.getWorkExperience());
        resumeForUpdate.setGithubUrl(resume.getGithubUrl());
        resumeForUpdate.setForeignLanguage(resume.getForeignLanguage());
        resumeForUpdate.setLinkedinUrl(resume.getLinkedinUrl());
        resumeForUpdate.setTechnology(resume.getTechnology());
        resumeForUpdate.setImageUrl(resume.getImageUrl());

        resumeRepository.save(resumeForUpdate);
    }

    @Override
    public void delete(Long id) {
        resumeRepository.deleteById(id);
    }

}