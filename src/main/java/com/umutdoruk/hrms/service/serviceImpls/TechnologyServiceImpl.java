package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.Technology;
import com.umutdoruk.hrms.repository.TechnologyRepository;
import com.umutdoruk.hrms.service.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<Technology> getAll() {
        return technologyRepository.findAll();
    }

    @Override
    public Technology findById(int id) {
        return technologyRepository.findById(id).get();
    }

    @Override
    public void add(Technology technology) {
        technologyRepository.save(technology);
    }


}
