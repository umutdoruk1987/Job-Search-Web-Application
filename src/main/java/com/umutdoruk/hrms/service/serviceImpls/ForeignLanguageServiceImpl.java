package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.ForeignLanguage;
import com.umutdoruk.hrms.exception.NotFoundException;
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
        if (foreignLanguage == null) {
            throw new NotFoundException("No foreign language record found to add");
        }
        foreignLanguageRepository.save(foreignLanguage);
    }

    @Override
    public void update(ForeignLanguage foreignLanguage) {
        ForeignLanguage foreignLanguageToUpdate = foreignLanguageRepository.findById(foreignLanguage.getForeignLanguageId())
                .orElseThrow(()-> new NotFoundException("Foreign Language is not found"));

        foreignLanguageToUpdate.setLanguageName(foreignLanguage.getLanguageName());
        foreignLanguageToUpdate.setLanguageLevel(foreignLanguage.getLanguageLevel());

        foreignLanguageRepository.save(foreignLanguageToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!(foreignLanguageRepository.existsById(id)))
            throw new NotFoundException("Foreign Language is not found");
        foreignLanguageRepository.deleteById(id);
    }

    @Override
    public ForeignLanguage getById(Long id) {
        return foreignLanguageRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Foreign Language is not found"));
    }

    @Override
    public List<ForeignLanguage> getAll() {
        return foreignLanguageRepository.findAll();
    }
}
