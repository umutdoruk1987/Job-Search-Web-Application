package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.ResumeRequest;
import com.umutdoruk.hrms.DTO.response.*;
import com.umutdoruk.hrms.entities.*;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.ResumeRepository;
import com.umutdoruk.hrms.service.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private WorkExperienceService workExperienceService;
    @Autowired
    private ForeignLanguageService foreignLanguageService;

    @Override
    public void create(ResumeRequest resumeRequest) {
        if (resumeRequest == null) {
            throw new NotFoundException("No Resume record found to add");
        }

        Resume resume = new Resume();
        resume.setCoverLetter(resumeRequest.getCoverLetter());
        resume.setGithubUrl(resumeRequest.getGithubUrl());
        resume.setLinkedinUrl(resumeRequest.getLinkedinUrl());
        resume.setImageUrl(resumeRequest.getImageUrl());
        resume.setCreateDate(LocalDate.now());
        resume.setActive(resumeRequest.getActive());
        resume.setCandidate(candidateService.getCandidateById(resumeRequest.getCandidateId()));
        resumeRepository.save(resume);
    }

    @Override
    public void update(ResumeRequest resumeRequest) {

        Resume resume = resumeRepository.findById(resumeRequest.getResumeId())
                .orElseThrow(()-> new NotFoundException("No Resume with this Id in Repository"));


        resume.setCoverLetter(resumeRequest.getCoverLetter());
        resume.setGithubUrl(resumeRequest.getGithubUrl());
        resume.setLinkedinUrl(resumeRequest.getLinkedinUrl());
        resume.setImageUrl(resumeRequest.getImageUrl());
        resume.setActive(resumeRequest.getActive());
        resumeRepository.save(resume);
    }

    @Override
    public void delete(Long id) {

        if (!(resumeRepository.existsById(id)))
            throw new NotFoundException("Resume is not found");
        resumeRepository.deleteById(id);
    }

    @Override
    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Resume is not found"));
    }

    @Override
    public Resume getResumeByCandidateId(Long candidateId) {
        return resumeRepository.findResumeByCandidateId(candidateId)
                .orElseThrow(()-> new NotFoundException("Resume is not found"));
    }

    @Override
    public ResumeResponse getResumeResponseById(Long id) {

        Resume resume = getResumeById(id);

        return ResumeResponse.of(resume,
                educationService.getAllEducationResponsesInResume(id),
                technologyService.getAllTechnologiesResponsesInResume(id),
                workExperienceService.getAllWorkExperienceResponsesInResume(id),
                foreignLanguageService.getAllForeignLanguageResponsesInResume(id));
    }

    @Override
    public List<ResumeResponse> getAllResumeResponses() {
        List<ResumeResponse> resumeResponseList = new ArrayList<>();

        for (Resume resume : resumeRepository.findAll() ){
            List<EducationResponse> educationResponseList =
                    educationService.getAllEducationResponsesInResume(resume.getId());
            List<TechnologyResponse> technologyResponseList =
                    technologyService.getAllTechnologiesResponsesInResume(resume.getId());
            List<WorkExperienceResponse> workExperienceResponseList =
                    workExperienceService.getAllWorkExperienceResponsesInResume(resume.getId());
            List<ForeignLanguageResponse> foreignLanguageResponseList =
                    foreignLanguageService.getAllForeignLanguageResponsesInResume(resume.getId());
            CandidateResponse candidateResponse =
                    candidateService.getCandidateResponseById(resume.getCandidate().getId());

            resumeResponseList.add(ResumeResponse.of(resume,
                    educationResponseList,
                    technologyResponseList,
                    workExperienceResponseList,
                    foreignLanguageResponseList));}

        return resumeResponseList;
    }
}