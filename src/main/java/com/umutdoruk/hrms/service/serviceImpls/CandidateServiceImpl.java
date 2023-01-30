package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.CandidateRequest;
import com.umutdoruk.hrms.DTO.response.CandidateResponse;
import com.umutdoruk.hrms.entities.Candidate;
import com.umutdoruk.hrms.entities.Resume;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.CandidatesRepository;
import com.umutdoruk.hrms.service.services.CandidateService;
import com.umutdoruk.hrms.service.services.ResumeService;
import com.umutdoruk.hrms.service.services.UserService;
import com.umutdoruk.hrms.utilities.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidatesRepository candidatesRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ResumeService resumeService;

   /* @Autowired
    public CandidateServiceImpl(CandidatesRepository candidatesRepository,
                                UserService userService,
                                ResumeService resumeService) {
        this.candidatesRepository = candidatesRepository;
        this.userService = userService;
        this.resumeService = resumeService;
    }*/

    @Override
    public void create(CandidateRequest candidateRequest) {

       if (!Validators.candidateValidator(candidateRequest))
            throw new BadRequestException("Candidate must be in the correct format and complete");

       Candidate candidate = new Candidate();
       candidate.setFirstName(candidateRequest.getFirstName());
       candidate.setLastName(candidateRequest.getLastName());
       candidate.setYearOfBirth(candidateRequest.getYearOfBirth());
       candidate.setTelephoneNumber(candidateRequest.getTelephoneNumber());
       candidate.setResume(new Resume());
       candidate.setUser(userService.getUserById(candidateRequest.getUserId()));

        candidatesRepository.save(candidate);
    }

    @Override
    public void update(CandidateRequest candidateRequest) {

        if (candidateRequest == null)
            throw new NotFoundException("No Candidate record found to update");

        Candidate candidate = getCandidateById(candidateRequest.getCandidateId());

        if (candidateRequest.getFirstName()!=null)candidate.setFirstName(candidateRequest.getFirstName());
        if (candidateRequest.getLastName()!=null)candidate.setLastName(candidateRequest.getLastName());
        if (candidateRequest.getYearOfBirth()!=null)candidate.setYearOfBirth(candidateRequest.getYearOfBirth());
        if (candidateRequest.getTelephoneNumber()!=null)candidate.setTelephoneNumber(candidateRequest.getTelephoneNumber());
        /*candidate.setUser(userService.getUserById(candidateRequest.getUserId()));*/
        /*candidate.setResume(resumeService.getResumeById(candidateRequest.getResumeId()));*/

        candidatesRepository.save(candidate);
    }

    @Override
    public void delete(Long id) {

        if (!(candidatesRepository.existsById(id)))
            throw new NotFoundException("Candidate is not found");
        candidatesRepository.deleteById(id);
    }

    @Override
    public Candidate getCandidateById(Long id) {
        return candidatesRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Candidate is not found"));
    }

    @Override
    public CandidateResponse getCandidateResponseById(Long id) {
        Candidate candidate = getCandidateById(id);
        return CandidateResponse.of(candidate,candidate.getResume().getId(),candidate.getUser().getId());
    }

    /*@Override
    public CandidateResponse getCandidateResponseByEmail(String email) {
        Candidate candidate = candidatesRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("Candidate is not found"));
        return CandidateResponse.of(candidate, candidate.getResume().getId(), candidate.getUser().getId());
    }
*/
    @Override
    public List<CandidateResponse> getAllCandidateResponses() {

        List<CandidateResponse> candidateResponseList = new ArrayList<>();

        for (Candidate candidate : candidatesRepository.findAll()){

            candidateResponseList.add(CandidateResponse.of(candidate,
                    candidate.getResume().getId(),
                    candidate.getUser().getId()));
        }

        if (candidateResponseList.size()==0)
            throw new NotFoundException("Any Candidate record isn't found");

        return candidateResponseList;
    }
}
