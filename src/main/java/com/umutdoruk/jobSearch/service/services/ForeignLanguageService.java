package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.ForeignLanguageRequest;
import com.umutdoruk.jobSearch.dto.response.ForeignLanguageResponse;
import com.umutdoruk.jobSearch.entities.ForeignLanguage;

import java.util.List;

public interface ForeignLanguageService {

    ForeignLanguageResponse create(ForeignLanguageRequest foreignLanguageRequest);
    ForeignLanguageResponse update(ForeignLanguageRequest foreignLanguageRequest);
    void delete(Long id);
    ForeignLanguage getForeignLanguageById(Long id);
    ForeignLanguageResponse getForeignLanguageResponseById(Long id);
    List<ForeignLanguageResponse> getAllForeignLanguageResponsesInResume(Long resumeId);
}
