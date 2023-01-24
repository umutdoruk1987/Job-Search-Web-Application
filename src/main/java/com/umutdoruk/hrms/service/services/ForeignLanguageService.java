package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.ForeignLanguageRequest;
import com.umutdoruk.hrms.DTO.response.ForeignLanguageResponse;

import java.util.List;

public interface ForeignLanguageService {

    void add(ForeignLanguageRequest foreignLanguageRequest);
    void update(ForeignLanguageRequest foreignLanguageRequest);
    void delete(Long id);
    ForeignLanguageResponse getById(Long id);
    List<ForeignLanguageResponse> getAll();
}
