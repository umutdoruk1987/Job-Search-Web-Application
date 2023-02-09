package com.umutdoruk.jobSearch.service.serviceImpls;

import com.umutdoruk.jobSearch.dto.request.WorkExperienceRequest;
import com.umutdoruk.jobSearch.dto.response.WorkExperienceResponse;
import com.umutdoruk.jobSearch.entities.WorkExperience;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.WorkExperienceRepository;
import com.umutdoruk.jobSearch.service.services.ResumeService;
import com.umutdoruk.jobSearch.service.services.WorkExperienceService;
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
    public WorkExperienceResponse create(WorkExperienceRequest workExperienceRequest) {

        if (workExperienceRequest == null)
            throw new NotFoundException("No Work Experience record found to add");

        WorkExperience workExperience = new WorkExperience();
        workExperience.setJobPositionName(workExperienceRequest.getJobPositionName());
        workExperience.setJobName(workExperienceRequest.getJobName());
        workExperience.setStartDate(workExperienceRequest.getStartDate());
        workExperience.setEndDate(workExperienceRequest.getEndDate());
        workExperience.setResume(resumeService.getResumeFromAuthentication());

        return WorkExperienceResponse.of(workExperienceRepository.save(workExperience));
    }

    @Override
    public WorkExperienceResponse update(WorkExperienceRequest workExperienceRequest) {

        if (workExperienceRequest == null)
            throw new NotFoundException("No Work Experience record found to update");

        if (!isWorkExperienceBelongedToUser(workExperienceRequest.getWorkExperienceId()))
            throw new BadRequestException("You have no such work experience");

        WorkExperience workExperience = workExperienceRepository.findById(workExperienceRequest.getWorkExperienceId()).get();
//                .orElseThrow(()-> new NotFoundException("No Work Experience with this Id in Repository"));

        if (workExperienceRequest.getJobName()!=null)workExperience.setJobName(workExperienceRequest.getJobName());
        if (workExperienceRequest.getJobPositionName()!=null)workExperience.setJobPositionName(workExperienceRequest.getJobPositionName());
        if (workExperienceRequest.getStartDate()!=null)workExperience.setStartDate(workExperienceRequest.getStartDate());
        if (workExperienceRequest.getEndDate()!=null)workExperience.setEndDate(workExperienceRequest.getEndDate());
        workExperience.setResume(resumeService.getResumeFromAuthentication());

        return WorkExperienceResponse.of(workExperienceRepository.save(workExperience));
    }

    @Override
    public void delete(Long id) {
        /*if (!(workExperienceRepository.existsById(id)))
            throw new NotFoundException("No Work Experience found to delete");*/
        if (!isWorkExperienceBelongedToUser(id)) throw new BadRequestException("You have no such work experience");
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

    private boolean isWorkExperienceBelongedToUser(Long workExperienceId){
        long count = resumeService.getResumeFromAuthentication().getWorkExperienceList()
                .stream()
                .filter(workExperience -> workExperience.getId().equals(workExperienceId)).count();

        return count==1;
    }

}

