package com.umutdoruk.jobSearch.service.serviceImpls;

import com.umutdoruk.jobSearch.dto.request.ResumeRequest;
import com.umutdoruk.jobSearch.dto.response.*;
import com.umutdoruk.jobSearch.entities.*;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.ResumeRepository;
import com.umutdoruk.jobSearch.service.services.*;
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
    @Autowired
    private UserService userService;

    @Override
    public ResumeResponse create(ResumeRequest resumeRequest) {
        Resume resume = new Resume();
        resume.setCreateDate(LocalDate.now());
        resume.setCandidate(candidateService.getCandidateById(resumeRequest.getCandidateId()));
        return ResumeResponse.of(resumeRepository.save(resume),null,null,null,null);
    }

    @Override
    public ResumeResponse update(ResumeRequest resumeRequest) {
        Resume resume = resumeUpdater(resumeRequest);
        resumeRepository.save(resume);
        return getResumeResponseById(resume.getId());
    }

    @Override
    public void delete(/*Long id*/) {
        Resume resume = getResumeFromAuthentication();
        educationService.getAllEducationResponsesInResume(resume.getId())
                .forEach(educationResponse -> educationService.delete(educationResponse.getEducationId()));
        workExperienceService.getAllWorkExperienceResponsesInResume(resume.getId())
                .forEach(workExperienceResponse -> workExperienceService.delete(workExperienceResponse.getWorkExperienceId()));
        foreignLanguageService.getAllForeignLanguageResponsesInResume(resume.getId())
                .forEach(foreignLanguageResponse -> foreignLanguageService.delete(foreignLanguageResponse.getForeignLanguageId()));
        technologyService.getAllTechnologiesResponsesInResume(resume.getId())
                .forEach(technologyResponse -> technologyService.delete(technologyResponse.getTechnologyId()));
        resumeRepository.deleteById(resume.getId());
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
    public ResumeResponse getResumeResponseByCandidateId(Long candidateId) {
        Resume resume = getResumeByCandidateId(candidateId);
        return getResumeResponseById(resume.getId());
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

            resumeResponseList.add(ResumeResponse.of(resume,
                    educationResponseList,
                    technologyResponseList,
                    workExperienceResponseList,
                    foreignLanguageResponseList));}

        return resumeResponseList;
    }

    private Resume resumeUpdater(ResumeRequest resumeRequest){
        Resume resume = getResumeFromAuthentication();
        if(resumeRequest.getCoverLetter()!=null)resume.setCoverLetter(resumeRequest.getCoverLetter());
        if(resumeRequest.getGithubUrl()!=null)resume.setGithubUrl(resumeRequest.getGithubUrl());
        if(resumeRequest.getLinkedinUrl()!=null)resume.setLinkedinUrl(resumeRequest.getLinkedinUrl());
        if(resumeRequest.getImageUrl()!=null)resume.setImageUrl(resumeRequest.getImageUrl());
        if(resumeRequest.getActive()!=null)resume.setActive(resumeRequest.getActive());
        return resume;
    }

    public Resume getResumeFromAuthentication(){
        User user = userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication());
        Candidate candidate = candidateService.getCandidateByUserId(user.getId());
        return getResumeByCandidateId(candidate.getId());
    }

}