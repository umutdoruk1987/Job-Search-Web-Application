package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.ForeignLanguageRequest;
import com.umutdoruk.hrms.DTO.response.ForeignLanguageResponse;
import com.umutdoruk.hrms.entities.ForeignLanguage;

import java.util.List;

public interface ForeignLanguageService {

    void create(ForeignLanguageRequest foreignLanguageRequest);
    void update(ForeignLanguageRequest foreignLanguageRequest, Long foreignLanguageId);
    void delete(Long id);
    ForeignLanguage getForeignLanguageById(Long id);
    ForeignLanguageResponse getForeignLanguageResponseById(Long id);
    List<ForeignLanguageResponse> getAllForeignLanguageResponsesInResume(Long resumeId);
}
