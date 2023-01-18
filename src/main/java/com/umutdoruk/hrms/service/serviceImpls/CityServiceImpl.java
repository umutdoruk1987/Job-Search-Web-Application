package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.concretes.City;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.CityRepository;
import com.umutdoruk.hrms.service.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getById(int id) {
        return cityRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("City is not found"));
    }
}

