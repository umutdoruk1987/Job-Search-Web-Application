package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.concretes.ForeignLanguage;
import com.umutdoruk.hrms.repository.ForeignLanguageRepository;
import com.umutdoruk.hrms.service.services.ForeignLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForeignLanguageServiceImpl implements ForeignLanguageService {

    private final ForeignLanguageRepository foreignLanguageRepository;

    @Autowired
    public ForeignLanguageServiceImpl(ForeignLanguageRepository foreignLanguageRepository) {
        this.foreignLanguageRepository = foreignLanguageRepository;
    }

    @Override
    public void add(ForeignLanguage foreignLanguage) {
        foreignLanguageRepository.save(foreignLanguage);
    }

    @Override
    public List<ForeignLanguage> getAll() {
        return foreignLanguageRepository.findAll();
    }
}
