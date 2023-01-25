package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.CityRequest;
import com.umutdoruk.hrms.DTO.response.CityResponse;
import com.umutdoruk.hrms.entities.City;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.CityRepository;
import com.umutdoruk.hrms.service.services.CityService;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, JobAdvertisementService jobAdvertisementService) {
        this.cityRepository = cityRepository;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @Override
    public void create(CityRequest cityRequest) {

        if (cityRequest == null)
            throw new NotFoundException("No City record found to add");

        City city = new City();
        city.setCityName(cityRequest.getCityName());
        city.setJobAdvertisement(jobAdvertisementService.findById(cityRequest.getJobAdvertisementId()));

        cityRepository.save(city);
    }

    @Override
    public void update(CityRequest cityRequest, Long cityId) {

        City city = cityRepository.findById(cityId)
                        .orElseThrow(()-> new NotFoundException("No City with this Id in Repository"));

        if (cityRequest == null)
            throw new NotFoundException("No City record found to update");

        city.setCityName(cityRequest.getCityName());
        city.setJobAdvertisement(jobAdvertisementService.findById(cityRequest.getJobAdvertisementId()));

        cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        if (!(cityRepository.existsById(id)))
            throw new NotFoundException("No City found to delete");
        cityRepository.deleteById(id);
    }

    @Override
    public CityResponse getById(Long id) {
         City city = cityRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("City is not found"));
        return CityResponse.of(city);
    }

}

