package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.CandidateRequest;
import com.umutdoruk.hrms.DTO.response.*;
import com.umutdoruk.hrms.entities.Candidate;
import com.umutdoruk.hrms.entities.Resume;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.CandidatesRepository;
import com.umutdoruk.hrms.service.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidatesRepository candidatesRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private ForeignLanguageService foreignLanguageService;
    @Autowired
    private WorkExperienceService workExperienceService;
    @Autowired
    private TechnologyService technologyService;

    @Override
    public void create(CandidateRequest candidateRequest) {

        Candidate candidate = candidateCreator(candidateRequest);
        candidatesRepository.save(candidate);
       /* Resume resume = resumeService.getResumeById(candidate.getResume().getId());
        resume.setCandidate(candidate);
        ResumeRequest resumeRequest = new ResumeRequest();
        resumeRequest.setResumeId(resume.getId());
        resumeService.update(resumeRequest);*/
    }

    @Override
    public void update(CandidateRequest candidateRequest) {

        candidatesRepository.save(candidateUpdater(candidateRequest));
    }

    @Override
    public Candidate getCandidateById(Long id) {
        return candidatesRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Candidate is not found"));
    }

    @Override
    public Candidate getCandidateByUserId(Long userId) {
        return candidatesRepository.findCandidateByUserId(userId);
    }

    @Override
    public CandidateResponse getCandidateResponseById(Long id) {

        Candidate candidate = getCandidateById(id);
        Resume resume = resumeService.getResumeByCandidateId(candidate.getId());
        Long resumeId = resume.getId();
        ResumeResponse resumeResponse = ResumeResponse.of(resume,educationService.getAllEducationResponsesInResume(resumeId),
                technologyService.getAllTechnologiesResponsesInResume(resumeId),
                workExperienceService.getAllWorkExperienceResponsesInResume(resumeId),
                foreignLanguageService.getAllForeignLanguageResponsesInResume(resumeId));
        return CandidateResponse.of(candidate,resumeResponse);
    }

    @Override
    public List<CandidateResponse> getAllCandidateResponses() {

        return candidatesRepository.findAll()
                .stream()
                .map(candidate -> getCandidateResponseById(candidate.getId())).collect(Collectors.toList());
         /* if (candidateResponseList.size()==0)
            throw new NotFoundException("Any Candidate record isn't found");*/
    }

    private void candidateCreateValidator(CandidateRequest candidateRequest){
        if (candidateRequest.getFirstName()==null || candidateRequest.getLastName()==null)
            throw new BadRequestException("First name and surname fields have to be filled");
        if (candidateRequest.getTelephoneNumber()!=null && candidateRequest.getTelephoneNumber().length()!=11)
            throw new BadRequestException("Telephone number can only have 11 digits.");
        if (getCandidateByUserId(candidateRequest.getUserId())!=null)
            throw new BadRequestException("A user cannot have more than one account");
        if (employerService.getEmployerByUserId(candidateRequest.getUserId())!=null)
            throw new BadRequestException("A user cannot have more than one role");
    }
    private Candidate candidateCreator (CandidateRequest candidateRequest){

        candidateCreateValidator(candidateRequest);

        Candidate candidate = new Candidate();
        candidate.setFirstName(candidateRequest.getFirstName());
        candidate.setLastName(candidateRequest.getLastName());
        candidate.setYearOfBirth(candidateRequest.getYearOfBirth());
        candidate.setTelephoneNumber(candidateRequest.getTelephoneNumber());
        /*resumeService.create(new ResumeRequest());*/
       /* candidate.setResume(new Resume());*/

        candidate.setUser(userService.getUserById(candidateRequest.getUserId()));

        return candidate;
    }

    private void candidateUpdateValidator(CandidateRequest candidateRequest){
        if (candidateRequest == null)
            throw new NotFoundException("No Candidate record found to update");
        if (candidateRequest.getTelephoneNumber()!=null && candidateRequest.getTelephoneNumber().length()!=11)
            throw new BadRequestException("Telephone number can only have 11 digits.");
    }
    private Candidate candidateUpdater(CandidateRequest candidateRequest){

       candidateUpdateValidator(candidateRequest);
       Candidate candidate = getCandidateById(candidateRequest.getCandidateId());

       if (candidateRequest.getFirstName()!=null)candidate.setFirstName(candidateRequest.getFirstName());
       if (candidateRequest.getLastName()!=null)candidate.setLastName(candidateRequest.getLastName());
       if (candidateRequest.getYearOfBirth()!=null)candidate.setYearOfBirth(candidateRequest.getYearOfBirth());
       if (candidateRequest.getTelephoneNumber()!=null && candidateRequest.getTelephoneNumber().length()==11)
            candidate.setTelephoneNumber(candidateRequest.getTelephoneNumber());

        return candidate;
    }


}
