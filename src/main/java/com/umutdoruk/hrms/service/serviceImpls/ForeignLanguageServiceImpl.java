package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.ForeignLanguageRequest;
import com.umutdoruk.hrms.DTO.response.ForeignLanguageResponse;
import com.umutdoruk.hrms.entities.ForeignLanguage;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.ForeignLanguageRepository;
import com.umutdoruk.hrms.service.services.ForeignLanguageService;
import com.umutdoruk.hrms.service.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForeignLanguageServiceImpl implements ForeignLanguageService {

    private final ForeignLanguageRepository foreignLanguageRepository;
    private final ResumeService resumeService;

    @Autowired
    public ForeignLanguageServiceImpl(ForeignLanguageRepository foreignLanguageRepository,ResumeService resumeService ) {
        this.foreignLanguageRepository = foreignLanguageRepository;
        this.resumeService = resumeService;
    }

    @Override
    public void create(ForeignLanguageRequest foreignLanguageRequest) {
        if (foreignLanguageRequest == null) {
            throw new NotFoundException("No foreign language record found to add");
        }
        ForeignLanguage foreignLanguage = new ForeignLanguage();
        foreignLanguage.setLanguageName(foreignLanguageRequest.getLanguageName());
        foreignLanguage.setLanguageLevel(foreignLanguageRequest.getLanguageLevel());
        foreignLanguage.setResume(resumeService.getResumeById(foreignLanguageRequest.getResumeId()));

        foreignLanguageRepository.save(foreignLanguage);
    }

    @Override
    public void update(ForeignLanguageRequest foreignLanguageRequest) {

        if (foreignLanguageRequest==null)
            throw new NotFoundException("No foreign language record found to update");

        ForeignLanguage foreignLanguage = foreignLanguageRepository.findById(foreignLanguageRequest.getForeignLanguageId())
                .orElseThrow(()-> new NotFoundException("No foreign Language with this Id in Repository"));

        foreignLanguage.setLanguageName(foreignLanguageRequest.getLanguageName());
        foreignLanguage.setLanguageLevel(foreignLanguageRequest.getLanguageLevel());
        foreignLanguage.setResume(resumeService.getResumeById(foreignLanguageRequest.getResumeId()));

        foreignLanguageRepository.save(foreignLanguage);
    }

    @Override
    public void delete(Long id) {
        if (!(foreignLanguageRepository.existsById(id)))
            throw new NotFoundException("No foreign Language found to delete");
        foreignLanguageRepository.deleteById(id);
    }

    @Override
    public ForeignLanguage getForeignLanguageById(Long id) {
        return foreignLanguageRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Foreign Language is not found"));
    }

    @Override
    public ForeignLanguageResponse getForeignLanguageResponseById(Long id) {
        return ForeignLanguageResponse.of(getForeignLanguageById(id));
    }

    @Override
    public List<ForeignLanguageResponse> getAllForeignLanguageResponsesInResume(Long resumeId) {

        return foreignLanguageRepository.findAllByResumeId(resumeId)
                .stream()
                .map(foreignLanguage -> ForeignLanguageResponse.of(foreignLanguage))
                .collect(Collectors.toList());
    }

}
