package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.concretes.TypeOfWork;
import com.umutdoruk.hrms.repository.TypeOfWorkRepository;
import com.umutdoruk.hrms.service.services.TypeOfWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfWorkServiceImpl implements TypeOfWorkService {

    private final TypeOfWorkRepository typeOfWorkRepository;

    @Autowired
    public TypeOfWorkServiceImpl(TypeOfWorkRepository typeOfWorkRepository) {
        this.typeOfWorkRepository = typeOfWorkRepository;
    }

    @Override
    public void add(TypeOfWork typeOfWork) {
        typeOfWorkRepository.save(typeOfWork);
    }

    @Override
    public List<TypeOfWork> getAll() {
        return typeOfWorkRepository.findAll();
    }

    @Override
    public TypeOfWork getById(int id) {
        return typeOfWorkRepository.findById(id).get();
    }
}