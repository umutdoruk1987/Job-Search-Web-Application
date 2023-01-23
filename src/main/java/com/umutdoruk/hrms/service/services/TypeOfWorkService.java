package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.entities.TypeOfWork;

import java.util.List;

public interface TypeOfWorkService {

    void add(TypeOfWork typeOfWork);
    List<TypeOfWork> getAll();
    TypeOfWork getById(Long id);
    void delete(Long id);
    void update(TypeOfWork typeOfWork);
}
