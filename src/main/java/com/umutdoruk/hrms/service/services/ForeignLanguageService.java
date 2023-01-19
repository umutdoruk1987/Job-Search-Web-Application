package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.concretes.ForeignLanguage;

import java.util.List;

public interface ForeignLanguageService {

    void add(ForeignLanguage foreignLanguage);
    List<ForeignLanguage> getAll();
}
