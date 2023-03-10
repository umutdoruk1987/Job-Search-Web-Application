package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.ForeignLanguageRequest;
import com.umutdoruk.jobSearch.dto.candidate.ForeignLanguageResponse;
import com.umutdoruk.jobSearch.entities.candidate.ForeignLanguage;
import com.umutdoruk.jobSearch.enums.LanguageLevelConstants;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.candidate.ForeignLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForeignLanguageServiceImpl implements ForeignLanguageService {

    private final ForeignLanguageRepository foreignLanguageRepository;
    private final ResumeService resumeService;

    @Autowired
    public ForeignLanguageServiceImpl(ForeignLanguageRepository foreignLanguageRepository,
                                      ResumeService resumeService ) {
        this.foreignLanguageRepository = foreignLanguageRepository;
        this.resumeService = resumeService;
    }

    @Override
    public ForeignLanguageResponse create(ForeignLanguageRequest foreignLanguageRequest) {
        if (foreignLanguageRequest == null) {
            throw new NotFoundException("No foreign language record found to add");
        }
        ForeignLanguage foreignLanguage = new ForeignLanguage();
        foreignLanguage.setLanguageName(foreignLanguageRequest.getLanguageName());
        foreignLanguage.setLanguageLevel(LanguageLevelConstants.findByName(foreignLanguageRequest.getLanguageLevel()));
        foreignLanguage.setResume(resumeService.getResumeFromAuthentication());
        return ForeignLanguageResponse.of(foreignLanguageRepository.save(foreignLanguage));
    }

    @Override
    public ForeignLanguageResponse update(ForeignLanguageRequest foreignLanguageRequest) {

        if (foreignLanguageRequest==null)
            throw new NotFoundException("No foreign language record found to update");
        if (!isForeignLanguageBelongedToUser(foreignLanguageRequest.getForeignLanguageId()))
            throw new BadRequestException("You have no such foreign language");

        ForeignLanguage foreignLanguage =
                foreignLanguageRepository.findById(foreignLanguageRequest.getForeignLanguageId()).get();
               /* .orElseThrow(()-> new NotFoundException("No foreign Language with this Id in Repository"));*/

        if (foreignLanguageRequest.getLanguageName()!=null)
            foreignLanguage.setLanguageName(foreignLanguageRequest.getLanguageName());
        if (foreignLanguageRequest.getLanguageLevel()!=null)
            foreignLanguage.setLanguageLevel(LanguageLevelConstants.findByName(foreignLanguageRequest.getLanguageLevel()));

        foreignLanguage.setResume(resumeService.getResumeFromAuthentication());
        return ForeignLanguageResponse.of(foreignLanguageRepository.save(foreignLanguage));
    }

    @Override
    public void delete(Long id) {
        /*if (!(foreignLanguageRepository.existsById(id)))
            throw new NotFoundException("No foreign Language found to delete");*/

        if (!isForeignLanguageBelongedToUser(id))
            throw new BadRequestException("You have no such foreign language");
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

    private boolean isForeignLanguageBelongedToUser(Long foreignLanguageId){
        long count = resumeService.getResumeFromAuthentication().getForeignLanguageList()
                .stream()
                .filter(foreignLanguage -> foreignLanguage.getId().equals(foreignLanguageId)).count();

        return count==1;
    }

}
