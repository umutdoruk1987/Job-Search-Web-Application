package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.WorkExperience;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.WorkExperienceRepository;
import com.umutdoruk.hrms.service.services.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;

    @Autowired
    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }

    @Override
    public void add(WorkExperience workExperience) {
        workExperienceRepository.save(workExperience);
    }

    @Override
    public List<WorkExperience> findAllByOrder() {
        return workExperienceRepository.findAllByOrderByEndDateDesc();
    }

    @Override
    public List<WorkExperience> getAll() {
        return workExperienceRepository.findAll();
    }

    @Override
    public WorkExperience getById(Long id) {
        return workExperienceRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Work Experience is not found"));
    }

    @Override
    public void delete(Long id) {
        if (!(workExperienceRepository.existsById(id)))
            throw new NotFoundException("Work Experience is not found");
        workExperienceRepository.deleteById(id);
    }

    @Override
    public void update(WorkExperience workExperience) {

        WorkExperience workExperienceForUpdate = workExperienceRepository.findById(workExperience.getId())
                .orElseThrow(()-> new NotFoundException("Work Experience is not found"));

        workExperienceForUpdate.setJobName(workExperience.getJobName());
        workExperienceForUpdate.setJobPositionName(workExperience.getJobPositionName());
        workExperienceForUpdate.setStartDate(workExperience.getStartDate());
        workExperienceForUpdate.setEndDate(workExperience.getEndDate());

        workExperienceRepository.save(workExperienceForUpdate);
    }
}

