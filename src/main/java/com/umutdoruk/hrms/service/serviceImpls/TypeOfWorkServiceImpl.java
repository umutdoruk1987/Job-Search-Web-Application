package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.TypeOfWorkRequest;
import com.umutdoruk.hrms.DTO.response.TypeOfWorkResponse;
import com.umutdoruk.hrms.entities.TypeOfWork;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.TypeOfWorkRepository;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import com.umutdoruk.hrms.service.services.TypeOfWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeOfWorkServiceImpl implements TypeOfWorkService {

    private final TypeOfWorkRepository typeOfWorkRepository;
    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    public TypeOfWorkServiceImpl(TypeOfWorkRepository typeOfWorkRepository, JobAdvertisementService jobAdvertisementService) {
        this.typeOfWorkRepository = typeOfWorkRepository;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @Override
    public void create(TypeOfWorkRequest typeOfWorkRequest) {

        if (typeOfWorkRequest == null) throw new NotFoundException("No Type of Work record found to add");

        if (getTypeOfWorkByJobAdvertisementId(typeOfWorkRequest.getJobAdvertisementId())!= null)
            throw new BadRequestException("You have already created a type of work. You can update it.");

        TypeOfWork typeOfWork = new TypeOfWork();
        typeOfWork.setName(typeOfWorkRequest.getName());
        typeOfWork.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(typeOfWorkRequest.getJobAdvertisementId()));

        typeOfWorkRepository.save(typeOfWork);
    }

    @Override
    public void update(TypeOfWorkRequest typeOfWorkRequest) {

        if (typeOfWorkRequest == null)
            throw new NotFoundException("No Type of Work record found to update");

        TypeOfWork typeOfWork = getTypeOfWorkById(typeOfWorkRequest.getTypeOfWorksId());
        typeOfWork.setName(typeOfWorkRequest.getName());
        typeOfWork.setJobAdvertisement(jobAdvertisementService.getJobAdvertisementById(typeOfWorkRequest.getJobAdvertisementId()));
        typeOfWorkRepository.save(typeOfWork);
    }

    @Override
    public void delete(Long id) {
        if (!(typeOfWorkRepository.existsById(id)))
            throw new NotFoundException("Type of Work is not found");
        typeOfWorkRepository.deleteById(id);
    }

    @Override
    public TypeOfWork getTypeOfWorkById(Long id) {
        return typeOfWorkRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Type of Work is not found"));
    }

    @Override
    public TypeOfWork getTypeOfWorkByJobAdvertisementId(Long id) {
        return typeOfWorkRepository.findByJobAdvertisementId(id);
    }

    @Override
    public TypeOfWorkResponse getTypeOfWorkResponseById(Long id) {
        return TypeOfWorkResponse.of(getTypeOfWorkById(id));
    }

    @Override
    public List<TypeOfWorkResponse> getAllTypeOfWorkResponses() {
        if (typeOfWorkRepository.findAll().isEmpty())
            throw new NotFoundException("Any Type of Work record isn't found");

        return typeOfWorkRepository.findAll()
                .stream()
                .map(typeOfWork -> TypeOfWorkResponse.of(typeOfWork))
                .collect(Collectors.toList());
    }
}