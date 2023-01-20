package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.Education;
import com.umutdoruk.hrms.repository.EducationRepository;
import com.umutdoruk.hrms.service.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public void add(Education education) {
        educationRepository.save(education);
    }

    @Override
    public List<Education> getAll() {
        return educationRepository.findAll();
    }

    @Override
    public List<Education> findAllByOrderByGraduationDateAsc() {
        return educationRepository.findAllByOrderByGraduationDateAsc();
    }

    @Override
    public List<Education> findAllByOrderByGraduationDateDesc() {
        return educationRepository.findAllByOrderByGraduationDateDesc();
    }
}