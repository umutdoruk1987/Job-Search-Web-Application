package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.Education;
import com.umutdoruk.hrms.exception.NotFoundException;
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
        if (education == null) {
            throw new NotFoundException("No Education record found to add");
        }
        educationRepository.save(education);
    }

    @Override
    public void update(Education education) {
        Education educationForUpdate = educationRepository.findById(education.getEducationId())
                .orElseThrow(()-> new NotFoundException("Education is not found"));

        educationForUpdate.setSchoolName(education.getSchoolName());
        educationForUpdate.setStartDate(education.getStartDate());
        educationForUpdate.setGraduationDate(education.getGraduationDate());

        educationRepository.save(educationForUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!(educationRepository.existsById(id)))
            throw new NotFoundException("Education is not found");
        educationRepository.deleteById(id);
    }

    @Override
    public Education getById(Long id) {
        return educationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Education is not found"));
    }

    @Override
    public List<Education> getAll() {
        if (educationRepository.findAll().isEmpty())
            throw new NotFoundException("Education is not found");
        return educationRepository.findAll();
    }

    @Override
    public List<Education> findAllByOrderByGraduationDateAsc() {
        if (educationRepository.findAllByOrderByGraduationDateAsc().isEmpty())
            throw new NotFoundException("Education is not found");
        return educationRepository.findAllByOrderByGraduationDateAsc();
    }

    @Override
    public List<Education> findAllByOrderByGraduationDateDesc() {
        if (educationRepository.findAllByOrderByGraduationDateDesc().isEmpty())
            throw new NotFoundException("Education is not found");
        return educationRepository.findAllByOrderByGraduationDateDesc();
    }
}