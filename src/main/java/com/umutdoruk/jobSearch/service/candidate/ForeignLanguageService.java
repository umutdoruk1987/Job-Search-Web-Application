package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.ForeignLanguageRequest;
import com.umutdoruk.jobSearch.dto.candidate.ForeignLanguageResponse;
import com.umutdoruk.jobSearch.entities.candidate.ForeignLanguage;

import java.util.List;

public interface ForeignLanguageService {

    ForeignLanguageResponse create(ForeignLanguageRequest foreignLanguageRequest);
    ForeignLanguageResponse update(ForeignLanguageRequest foreignLanguageRequest);
    void delete(Long id);
    ForeignLanguage getForeignLanguageById(Long id);
    ForeignLanguageResponse getForeignLanguageResponseById(Long id);
    List<ForeignLanguageResponse> getAllForeignLanguageResponsesInResume(Long resumeId);
}
