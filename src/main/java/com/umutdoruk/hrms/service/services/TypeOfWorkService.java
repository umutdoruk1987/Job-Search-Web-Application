package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.TypeOfWorkRequest;
import com.umutdoruk.hrms.DTO.response.TypeOfWorkResponse;

import java.util.List;

public interface TypeOfWorkService {

    void add(TypeOfWorkRequest typeOfWorkRequest);
    List<TypeOfWorkResponse> getAll();
    TypeOfWorkResponse getById(Long id);
    void delete(Long id);
    void update(TypeOfWorkRequest typeOfWorkRequest);
}
