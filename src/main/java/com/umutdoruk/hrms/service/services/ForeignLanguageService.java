package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.ForeignLanguage;

import java.util.List;

public interface ForeignLanguageService {

    void add(ForeignLanguage foreignLanguage);
    List<ForeignLanguage> getAll();
}
