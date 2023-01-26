package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.TypeOfWorkRequest;
import com.umutdoruk.hrms.DTO.response.TypeOfWorkResponse;
import com.umutdoruk.hrms.entities.TypeOfWork;

import java.util.List;

public interface TypeOfWorkService {

    void create(TypeOfWorkRequest typeOfWorkRequest);
    void update(TypeOfWorkRequest typeOfWorkRequest, Long typeOfWorksId);
    void delete(Long id);
    TypeOfWork getTypeOfWorkById(Long id);
    TypeOfWorkResponse getTypeOfWorkResponseById(Long id);
    List<TypeOfWorkResponse> getAllTypeOfWorkResponses();
}
