package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.WorkExperienceRequest;
import com.umutdoruk.hrms.DTO.response.WorkExperienceResponse;
import com.umutdoruk.hrms.entities.WorkExperience;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.WorkExperienceRepository;
import com.umutdoruk.hrms.service.services.ResumeService;
import com.umutdoruk.hrms.service.services.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final ResumeService resumeService;

    @Autowired
    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository, ResumeService resumeService) {
        this.workExperienceRepository = workExperienceRepository;
        this.resumeService = resumeService;
    }

    @Override
    public void create(WorkExperienceRequest workExperienceRequest) {

        if (workExperienceRequest == null)
            throw new NotFoundException("No Work Experience record found to add");

        WorkExperience workExperience = new WorkExperience();
        workExperience.setJobPositionName(workExperienceRequest.getJobPositionName());
        workExperience.setJobName(workExperienceRequest.getJobName());
        workExperience.setStartDate(workExperienceRequest.getStartDate());
        workExperience.setEndDate(workExperienceRequest.getEndDate());
        workExperience.setResume(resumeService.getResumeById(workExperienceRequest.getResumeId()));

        workExperienceRepository.save(workExperience);
    }

    @Override
    public void update(WorkExperienceRequest workExperienceRequest) {

        if (workExperienceRequest == null)
            throw new NotFoundException("No Work Experience record found to update");

        WorkExperience workExperience = workExperienceRepository.findById(workExperienceRequest.getWorkExperienceId())
                .orElseThrow(()-> new NotFoundException("No Work Experience with this Id in Repository"));

        workExperience.setJobName(workExperienceRequest.getJobName());
        workExperience.setJobPositionName(workExperienceRequest.getJobPositionName());
        workExperience.setStartDate(workExperienceRequest.getStartDate());
        workExperience.setEndDate(workExperienceRequest.getEndDate());
        workExperience.setResume(resumeService.getResumeById(workExperienceRequest.getResumeId()));

        workExperienceRepository.save(workExperience);
    }

    @Override
    public void delete(Long id) {
        if (!(workExperienceRepository.existsById(id)))
            throw new NotFoundException("No Work Experience found to delete");
        workExperienceRepository.deleteById(id);
    }

    @Override
    public WorkExperience getWorkExperienceById(Long id) {
        return workExperienceRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Work Experience is not found"));
    }

    @Override
    public WorkExperienceResponse getWorkExperienceResponseById(Long id) {
        return WorkExperienceResponse.of(getWorkExperienceById(id));
    }

    @Override
    public List<WorkExperienceResponse> getAllWorkExperienceResponsesInResume(Long resumeId) {

        return workExperienceRepository.findAllByResumeId(resumeId)
                .stream()
                .map(workExperience -> WorkExperienceResponse.of(workExperience))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkExperienceResponse> getAllWorkExperienceResponsesByOrderByEndDateDesc() {
        return workExperienceRepository.findAllByOrderByEndDateDesc()
                .stream()
                .map(workExperience -> WorkExperienceResponse.of(workExperience))
                .collect(Collectors.toList());
    }

}

