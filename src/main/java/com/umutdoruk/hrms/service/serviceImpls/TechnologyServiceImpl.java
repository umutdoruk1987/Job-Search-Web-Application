package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.Technology;
import com.umutdoruk.hrms.exception.NotFoundException;
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
        if (technologyRepository.findAll().isEmpty())
            throw new NotFoundException("Technology is not found");
        return technologyRepository.findAll();
    }

    @Override
    public Technology findById(Long id) {
        return technologyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Technology is not found"));
    }

    @Override
    public void delete(Long id) {
        if (!(technologyRepository.existsById(id)))
            throw new NotFoundException("Technology is not found");
        technologyRepository.deleteById(id);
    }

    @Override
    public void update(Technology technology) {
        Technology technologyForUpdate = technologyRepository.findById(technology.getId())
                .orElseThrow(()-> new NotFoundException("Technology is not found"));

        technologyForUpdate.setTechnologyName(technology.getTechnologyName());
        technologyRepository.save(technologyForUpdate);
    }

    @Override
    public void add(Technology technology) {

        if (technology == null)
            throw new NotFoundException("No Technology record found to add");
        technologyRepository.save(technology);
    }

}
