package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.City;
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
    public void create(City city) {
        cityRepository.save(city);
    }

    @Override
    public void update(String cityName) {
        City city = cityRepository.findByCityName(cityName);
        city.setCityName(cityName);
        cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("City is not found"));
    }

}

