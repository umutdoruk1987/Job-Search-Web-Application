package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.CandidateRequest;
import com.umutdoruk.jobSearch.dto.candidate.ResumeRequest;
import com.umutdoruk.jobSearch.dto.candidate.CandidateResponse;
import com.umutdoruk.jobSearch.dto.candidate.ResumeResponse;
import com.umutdoruk.jobSearch.entities.candidate.Candidate;
import com.umutdoruk.jobSearch.entities.candidate.Resume;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.candidate.CandidatesRepository;
import com.umutdoruk.jobSearch.service.user.UserServiceImpl;
import com.umutdoruk.jobSearch.service.employer.EmployerService;
import com.umutdoruk.jobSearch.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public CandidateResponse create(CandidateRequest candidateRequest) {
        Candidate candidate = candidatesRepository.save(candidateCreator(candidateRequest));

        ResumeRequest resumeRequest = new ResumeRequest();
        resumeRequest.setCandidateId(candidate.getId());
        ResumeResponse resumeResponse = resumeService.create(resumeRequest);
        return CandidateResponse.of(candidate,resumeResponse);

        //User user = new User() Burada login olan user i alacaz. Daha sonra onun userId sini alip asagi verecez/
        /*Long userId = 2L;
        Candidate candidateForResume =  getCandidateByUserId(userId);
        ResumeRequest resumeRequest = new ResumeRequest();
        resumeRequest.setCandidateId(candidateForResume.getId());
        resumeService.create(resumeRequest);*/
    }

    @Override
    public CandidateResponse update(CandidateRequest candidateRequest) {
        Candidate candidate = candidateUpdater(candidateRequest);
        candidatesRepository.save(candidate);

        Resume resume = resumeService.getResumeByCandidateId(candidate.getId());
        ResumeResponse resumeResponse = resumeService.getResumeResponseById(resume.getId());
        return CandidateResponse.of(candidate,resumeResponse);
    }

    @Override
    public void delete() {

        Candidate candidate = candidatesRepository
                .findCandidateByUserId(userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication()).getId());
        resumeService.delete();
        candidatesRepository.deleteById(candidate.getId());
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
        if(resume!=null) {
            Long resumeId = resume.getId();
            ResumeResponse resumeResponse = ResumeResponse.of(resume, educationService.getAllEducationResponsesInResume(resumeId),
                    technologyService.getAllTechnologiesResponsesInResume(resumeId),
                    workExperienceService.getAllWorkExperienceResponsesInResume(resumeId),
                    foreignLanguageService.getAllForeignLanguageResponsesInResume(resumeId));
            return CandidateResponse.of(candidate, resumeResponse);
        }
        return CandidateResponse.of(candidate,null);
    }

    @Override
    public List<CandidateResponse> getAllCandidateResponses() {

        return candidatesRepository.findAll()
                .stream()
                .map(candidate -> getCandidateResponseById(candidate.getId())).collect(Collectors.toList());
         /* if (candidateResponseList.size()==0)
            throw new NotFoundException("Any Candidate record isn't found");*/
    }

   /* private void candidateCreateValidator(CandidateRequest candidateRequest){

    }*/
    private Candidate candidateCreator (CandidateRequest candidateRequest){

        //candidateCreateValidator(candidateRequest);

        Candidate candidate = new Candidate();
        candidate.setFirstName(candidateRequest.getFirstName());
        candidate.setLastName(candidateRequest.getLastName());
        candidate.setYearOfBirth(candidateRequest.getYearOfBirth());
        candidate.setTelephoneNumber(candidateRequest.getTelephoneNumber());

        candidate.setUser(userService.getUserById(candidateRequest.getUserId()));

        return candidate;
    }

    private void candidateUpdateValidator(CandidateRequest candidateRequest){
        if (candidateRequest == null)
            throw new NotFoundException("No Candidate record found to update");
        if (candidateRequest.getFirstName()==null || candidateRequest.getLastName()==null)
            throw new BadRequestException("First name and surname fields have to be filled");
        if (candidateRequest.getTelephoneNumber()==null)
            throw new BadRequestException("Telephone number have to be filled");
        if (candidateRequest.getTelephoneNumber().length()!=11)
            throw new BadRequestException("Telephone number can only have 11 digits.");
        if (candidateRequest.getYearOfBirth()==null)
            throw new BadRequestException("Date of Birth have to be filled");
        if ((candidateRequest.getYearOfBirth().plusYears(16).isAfter(LocalDate.now())))
            throw new BadRequestException("Candidates under 16 cannot seek employment.");
        if (getCandidateByUserId(candidateRequest.getUserId())!=null)
            throw new BadRequestException("A user cannot have more than one account");
        if (employerService.getEmployerByUserId(candidateRequest.getUserId())!=null)
            throw new BadRequestException("A user cannot have more than one role");
    }
    private Candidate candidateUpdater(CandidateRequest candidateRequest){

       candidateUpdateValidator(candidateRequest);
       candidateRequest.setUserId(userService.getUserByUsername(UserServiceImpl.getUsernameFromAuthentication()).getId());
       Candidate candidate = getCandidateByUserId(candidateRequest.getUserId());

       /*if (candidateRequest.getFirstName()!=null)*/candidate.setFirstName(candidateRequest.getFirstName());
       /*if (candidateRequest.getLastName()!=null)*/candidate.setLastName(candidateRequest.getLastName());
       /*if (candidateRequest.getYearOfBirth()!=null)*/candidate.setYearOfBirth(candidateRequest.getYearOfBirth());
       /*if (candidateRequest.getTelephoneNumber()!=null && candidateRequest.getTelephoneNumber().length()==11)*/
            candidate.setTelephoneNumber(candidateRequest.getTelephoneNumber());

        return candidate;
    }


}
