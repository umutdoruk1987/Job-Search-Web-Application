package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.ForeignLanguageRequest;
import com.umutdoruk.hrms.DTO.response.ForeignLanguageResponse;
import com.umutdoruk.hrms.entities.ForeignLanguage;

import java.util.List;

public interface ForeignLanguageService {

    void add(ForeignLanguageRequest foreignLanguageRequest);
    void update(ForeignLanguageRequest foreignLanguageRequest, Long foreignLanguageId);
    void delete(Long id);
    ForeignLanguageResponse getById(Long id);
    ForeignLanguage findById(Long id);
    List<ForeignLanguageResponse> getAll(Long resumeId);
}
