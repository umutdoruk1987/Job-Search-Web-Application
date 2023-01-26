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
import java.util.stream.Collectors;

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
        resume.setEducationList(createEducationListFromIdList(resumeRequest));
        resume.setTechnologyList(createTechnologyListFromIdList(resumeRequest));
        resume.setWorkExperienceList(createWorkExperienceListFromIdList(resumeRequest));
        resume.setForeignLanguageList(createForeignLanguageListFromIdList(resumeRequest));

        resumeRepository.save(resume);
    }

    @Override
    public void update(ResumeRequest resumeRequest, Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(()-> new NotFoundException("No Resume with this Id in Repository"));

        if (resumeRequest == null)
            throw new NotFoundException("No Resume record found to update");

        resume.setCoverLetter(resumeRequest.getCoverLetter());
        resume.setGithubUrl(resumeRequest.getGithubUrl());
        resume.setLinkedinUrl(resumeRequest.getLinkedinUrl());
        resume.setImageUrl(resumeRequest.getImageUrl());
        resume.setActive(resumeRequest.getActive());
        resume.setEducationList(createEducationListFromIdList(resumeRequest));
        resume.setTechnologyList(createTechnologyListFromIdList(resumeRequest));
        resume.setWorkExperienceList(createWorkExperienceListFromIdList(resumeRequest));
        resume.setForeignLanguageList(createForeignLanguageListFromIdList(resumeRequest));

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
    public ResumeResponse getResumeResponseById(Long id) {

        Resume resume = getResumeById(id);

        return ResumeResponse.of(resume,
                candidateService.getCandidateResponseById(resume.getCandidate().getId()),
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
                    candidateResponse,
                    educationResponseList,
                    technologyResponseList,
                    workExperienceResponseList,
                    foreignLanguageResponseList));}

        return resumeResponseList;
    }

    private List<Education> createEducationListFromIdList (ResumeRequest resumeRequest){
        return resumeRequest.getEducationIdList()
                .stream()
                .map(educationId ->educationService.getEducationById(educationId))
                .collect(Collectors.toList());
    }

    private List<Technology> createTechnologyListFromIdList (ResumeRequest resumeRequest){
        return resumeRequest.getTechnologyIdList()
                .stream()
                .map(technologyId -> technologyService.getTechnologyById(technologyId))
                .collect(Collectors.toList());
    }

    private List<WorkExperience> createWorkExperienceListFromIdList (ResumeRequest resumeRequest){
        return resumeRequest.getWorkExperienceIdList()
                .stream()
                .map(workExperienceId-> workExperienceService.getWorkExperienceById(workExperienceId))
                .collect(Collectors.toList());
    }

    private List<ForeignLanguage> createForeignLanguageListFromIdList (ResumeRequest resumeRequest){
        return resumeRequest.getForeignLanguageIdList()
                .stream()
                .map(foreignLanguageId -> foreignLanguageService.getForeignLanguageById(foreignLanguageId))
                .collect(Collectors.toList());
    }
}