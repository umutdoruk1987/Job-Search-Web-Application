package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.ResumeRequest;
import com.umutdoruk.hrms.DTO.response.ResumeResponse;
import com.umutdoruk.hrms.entities.Resume;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.ResumeRepository;
import com.umutdoruk.hrms.service.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final CandidateService candidateService;
    private final EducationService educationService;
    private final TechnologyService technologyService;
    private final WorkExperienceService workExperienceService;
    private final ForeignLanguageService foreignLanguageService;


    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository,
                             CandidateService candidateService,
                             EducationService educationService,
                             TechnologyService technologyService,
                             WorkExperienceService workExperienceService,
                             ForeignLanguageService foreignLanguageService) {
        this.resumeRepository = resumeRepository;
        this.candidateService = candidateService;
        this.educationService = educationService;
        this.technologyService = technologyService;
        this.workExperienceService = workExperienceService;
        this.foreignLanguageService = foreignLanguageService;
    }

    @Override
    public void add(ResumeRequest resumeRequest) {
        if (resumeRequest == null) {
            throw new NotFoundException("No Resume record found to add");
        }
        resume.setCreateDate(LocalDate.now());
        resumeRepository.save(resume);
    }

    @Override
    public ResumeResponse findResumeById(Long id) {

        return ResumeResponse.of(resume,
                candidateService.findById(resume.getCandidate().getId()),
                educationService.getAll(),
                );
    }

    @Override
    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Resume is not found"));
    }

    @Override
    public List<ResumeResponse> getAll() {
        if (resumeRepository.findAll().isEmpty())
            throw new NotFoundException("Resume is not found");
        return resumeRepository.findAll();
    }

    @Override
    public void update(ResumeRequest resumeRequest) {
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