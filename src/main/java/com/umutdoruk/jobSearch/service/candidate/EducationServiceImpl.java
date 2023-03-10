package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.EducationRequest;
import com.umutdoruk.jobSearch.dto.candidate.EducationResponse;
import com.umutdoruk.jobSearch.entities.candidate.Education;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.candidate.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ResumeService resumeService;

    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository, ResumeService resumeService) {
        this.educationRepository = educationRepository;
        this.resumeService = resumeService;
    }

    @Override
    public EducationResponse create(EducationRequest educationRequest) {

        if (educationRequest == null)
            throw new NotFoundException("No Education record found to add");

        Education education = new Education();
        education.setSchoolName(educationRequest.getSchoolName());
        education.setStartDate(educationRequest.getStartDate());
        education.setGraduationDate(educationRequest.getGraduationDate());
        education.setResume(resumeService.getResumeFromAuthentication());
        return EducationResponse.of(educationRepository.save(education));
    }

    @Override
    public EducationResponse update(EducationRequest educationRequest) {

        if (educationRequest == null)
            throw new NotFoundException("No Education record found to update");
        if (!isEducationBelongedToUser(educationRequest.getEducationId()))
            throw new BadRequestException("You have no such education");

        Education education = educationRepository.findById(educationRequest.getEducationId()).get();
                /*.orElseThrow(()-> new NotFoundException("No education with this Id in Repository"));*/
        if (educationRequest.getSchoolName()!=null)education.setSchoolName(educationRequest.getSchoolName());
        if (educationRequest.getStartDate()!=null)education.setStartDate(educationRequest.getStartDate());
        if (educationRequest.getGraduationDate()!=null)education.setGraduationDate(educationRequest.getGraduationDate());
        education.setResume(resumeService.getResumeFromAuthentication());
        return EducationResponse.of(educationRepository.save(education));
    }

    @Override
    public void delete(Long id) {
        if (!isEducationBelongedToUser(id)) throw new BadRequestException("You have no such education");
        /*if (!(educationRepository.existsById(id))) throw new NotFoundException("No Education found to delete");*/
        educationRepository.deleteById(id);
    }

    @Override
    public Education getEducationById(Long id) {
        return educationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Education is not found"));
    }

    @Override
    public EducationResponse getEducationResponseById(Long id) {
        return EducationResponse.of(getEducationById(id));
    }

    @Override
    public List<EducationResponse> getAllEducationResponsesInResume(Long resumeId) {

        return educationRepository.findAllByResumeId(resumeId)
                .stream()
                .map(education -> EducationResponse.of(education))
                .collect(Collectors.toList());
    }

    @Override
    public List<EducationResponse> findAllByOrderByGraduationDateAsc() {
        return educationRepository.findAllByOrderByGraduationDateAsc()
                .stream()
                .map(education -> EducationResponse.of(education))
                .collect(Collectors.toList());
    }

    @Override
    public List<EducationResponse> findAllByOrderByGraduationDateDesc() {
        return educationRepository.findAllByOrderByGraduationDateDesc()
                .stream()
                .map(education -> EducationResponse.of(education))
                .collect(Collectors.toList());
    }

    private boolean isEducationBelongedToUser(Long educationId){
        long count = resumeService.getResumeFromAuthentication().getEducationList()
                .stream()
                .filter(education -> education.getId().equals(educationId)).count();

        return count==1;
    }
}