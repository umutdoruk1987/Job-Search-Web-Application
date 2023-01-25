package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.ForeignLanguageRequest;
import com.umutdoruk.hrms.DTO.response.ForeignLanguageResponse;
import com.umutdoruk.hrms.entities.ForeignLanguage;
import com.umutdoruk.hrms.entities.Resume;
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
    public void add(ForeignLanguageRequest foreignLanguageRequest) {
        if (foreignLanguageRequest == null) {
            throw new NotFoundException("No foreign language record found to add");
        }
        ForeignLanguage foreignLanguage = new ForeignLanguage();
        foreignLanguage.setLanguageName(foreignLanguageRequest.getLanguageName());
        foreignLanguage.setLanguageLevel(foreignLanguageRequest.getLanguageLevel());
        foreignLanguage.setResume(resumeService.getById(foreignLanguageRequest.getResumeId()));

        foreignLanguageRepository.save(foreignLanguage);
    }

    @Override
    public void update(ForeignLanguageRequest foreignLanguageRequest, Long foreignLanguageId) {
        ForeignLanguage foreignLanguage = foreignLanguageRepository.findById(foreignLanguageId)
                .orElseThrow(()-> new NotFoundException("No foreign Language with this Id in Repository"));

        if (foreignLanguageRequest==null)
            throw new NotFoundException("No foreign language record found to update");

        foreignLanguage.setLanguageName(foreignLanguageRequest.getLanguageName());
        foreignLanguage.setLanguageLevel(foreignLanguageRequest.getLanguageLevel());
        foreignLanguage.setResume(resumeService.getById(foreignLanguageRequest.getResumeId()));

        foreignLanguageRepository.save(foreignLanguage);
    }

    @Override
    public void delete(Long id) {
        if (!(foreignLanguageRepository.existsById(id)))
            throw new NotFoundException("No foreign Language found to delete");
        foreignLanguageRepository.deleteById(id);
    }

    @Override
    public ForeignLanguageResponse getById(Long id) {
        ForeignLanguage foreignLanguage = foreignLanguageRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Foreign Language is not found"));
        return ForeignLanguageResponse.of(foreignLanguage);
    }

    @Override
    public ForeignLanguage findById(Long id) {
        return foreignLanguageRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Foreign Language is not found"));
    }

    @Override
    public List<ForeignLanguageResponse> getAll(Long resumeId) {

        Resume resume = resumeService.getById(resumeId);

        List<ForeignLanguageResponse> foreignLanguageResponseList = resume.getForeignLanguageList()
                .stream()
                .map(foreignLanguage -> ForeignLanguageResponse.of(foreignLanguage))
                .collect(Collectors.toList());

        if (foreignLanguageResponseList.size()==0)
            throw new NotFoundException("Any Foreign Language record isn't found");
        return foreignLanguageResponseList;
    }

}
