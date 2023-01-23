package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.TypeOfWork;
import com.umutdoruk.hrms.exception.NotFoundException;
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
        if (typeOfWork == null)
            throw new NotFoundException("No Type of Work record found to add");
        typeOfWorkRepository.save(typeOfWork);
    }

    @Override
    public List<TypeOfWork> getAll() {
        if (typeOfWorkRepository.findAll().isEmpty())
            throw new NotFoundException("Type of Work is not found");
        return typeOfWorkRepository.findAll();
    }

    @Override
    public TypeOfWork getById(Long id) {
        return typeOfWorkRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Type of Work is not found"));
    }

    @Override
    public void update(TypeOfWork typeOfWork) {
        TypeOfWork typeOfWorkToUpdate = typeOfWorkRepository.findById(typeOfWork.getId())
                .orElseThrow(()-> new NotFoundException("Type of Work is not found"));

        typeOfWorkToUpdate.setName(typeOfWork.getName());
        typeOfWorkRepository.save(typeOfWorkToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!(typeOfWorkRepository.existsById(id)))
            throw new NotFoundException("Type of Work is not found");
        typeOfWorkRepository.deleteById(id);
    }
}