package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.ForeignLanguage;

import java.util.List;

public interface ForeignLanguageService {

    void add(ForeignLanguage foreignLanguage);
    void update(ForeignLanguage foreignLanguage);
    void delete(Long id);
    ForeignLanguage getById(Long id);
    List<ForeignLanguage> getAll();
}
