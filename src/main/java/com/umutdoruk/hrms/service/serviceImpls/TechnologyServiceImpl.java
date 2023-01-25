package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.TechnologyRequest;
import com.umutdoruk.hrms.DTO.response.TechnologyResponse;
import com.umutdoruk.hrms.entities.Resume;
import com.umutdoruk.hrms.entities.Technology;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.TechnologyRepository;
import com.umutdoruk.hrms.service.services.ResumeService;
import com.umutdoruk.hrms.service.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final ResumeService resumeService;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository, ResumeService resumeService) {
        this.technologyRepository = technologyRepository;
        this.resumeService = resumeService;
    }

    @Override
    public List<TechnologyResponse> getAll(Long resumeId) {

        Resume resume = resumeService.getResumeById(resumeId);

        List<TechnologyResponse> technologyResponseList = resume.getTechnologyList()
                .stream()
                .map(technology -> TechnologyResponse.of(technology))
                .collect(Collectors.toList());

        if (technologyResponseList.size()==0)
            throw new NotFoundException("Any Technology record isn't found");
        return technologyResponseList;
    }

    @Override
    public TechnologyResponse findById(Long id) {
       Technology technology = technologyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Technology is not found"));
        return TechnologyResponse.of(technology);
    }

    @Override
    public void delete(Long id) {
        if (!(technologyRepository.existsById(id)))
            throw new NotFoundException("No Technology found to delete");
        technologyRepository.deleteById(id);
    }

    @Override
    public void update(TechnologyRequest technologyRequest, Long technologyId) {

        Technology technology = technologyRepository.findById(technologyId)
                .orElseThrow(()-> new NotFoundException("No technology with this Id in Repository"));

        if (technologyRequest == null)
            throw new NotFoundException("No Technology record found to update");

        technology.setTechnologyName(technologyRequest.getTechnologyName());
        technology.setResume(resumeService.getResumeById(technologyRequest.getResumeId()));

        technologyRepository.save(technology);
    }

    @Override
    public void add(TechnologyRequest technologyRequest) {

        if (technologyRequest == null)
            throw new NotFoundException("No Technology record found to add");

        Technology technology = new Technology();
        technology.setTechnologyName(technologyRequest.getTechnologyName());
        technology.setResume(resumeService.getResumeById(technologyRequest.getResumeId()));

        technologyRepository.save(technology);
    }


}
