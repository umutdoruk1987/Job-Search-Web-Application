package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.EducationRequest;
import com.umutdoruk.hrms.DTO.response.EducationResponse;
import com.umutdoruk.hrms.entities.Education;
import com.umutdoruk.hrms.entities.Resume;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.EducationRepository;
import com.umutdoruk.hrms.service.services.EducationService;
import com.umutdoruk.hrms.service.services.ResumeService;
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
    public void add(EducationRequest educationRequest) {

        if (educationRequest == null)
            throw new NotFoundException("No Education record found to add");

        Education education = new Education();
        education.setSchoolName(educationRequest.getSchoolName());
        education.setStartDate(educationRequest.getStartDate());
        education.setGraduationDate(educationRequest.getGraduationDate());
        education.setResume(resumeService.getResumeById(educationRequest.getResumeId()));

        educationRepository.save(education);
    }

    @Override
    public void update(EducationRequest educationRequest, Long educationId) {

        Education education = educationRepository.findById(educationId)
                .orElseThrow(()-> new NotFoundException("No education with this Id in Repository"));

        if (educationRequest == null)
            throw new NotFoundException("No Education record found to update");

        education.setSchoolName(educationRequest.getSchoolName());
        education.setStartDate(educationRequest.getStartDate());
        education.setGraduationDate(educationRequest.getGraduationDate());
        education.setResume(resumeService.getResumeById(educationRequest.getResumeId()));

        educationRepository.save(education);
    }

    @Override
    public void delete(Long id) {

        if (!(educationRepository.existsById(id)))
            throw new NotFoundException("No Education found to delete");
        educationRepository.deleteById(id);
    }

    @Override
    public EducationResponse getById(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Education is not found"));
        return EducationResponse.of(education);
    }

    @Override
    public List<EducationResponse> getAll(Long resumeId) {

        Resume resume = resumeService.getResumeById(resumeId);

        List<EducationResponse> educationResponseList = resume.getEducationList()
                .stream()
                .map(education -> EducationResponse.of(education))
                .collect(Collectors.toList());

        if (educationResponseList.size()==0)
            throw new NotFoundException("Any Education record isn't found");
        return educationResponseList;
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
}