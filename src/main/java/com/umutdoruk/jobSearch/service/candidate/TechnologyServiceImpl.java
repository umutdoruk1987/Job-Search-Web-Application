package com.umutdoruk.jobSearch.service.candidate;

import com.umutdoruk.jobSearch.dto.candidate.TechnologyRequest;
import com.umutdoruk.jobSearch.dto.candidate.TechnologyResponse;
import com.umutdoruk.jobSearch.entities.candidate.Technology;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.repository.candidate.TechnologyRepository;
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
    public TechnologyResponse create(TechnologyRequest technologyRequest) {

        if (technologyRequest == null)
            throw new NotFoundException("No Technology record found to add");

        Technology technology = new Technology();
        technology.setTechnologyName(technologyRequest.getTechnologyName());
        technology.setResume(resumeService.getResumeFromAuthentication());

        return TechnologyResponse.of(technologyRepository.save(technology));
    }

    @Override
    public TechnologyResponse update(TechnologyRequest technologyRequest) {

        if (technologyRequest == null)
            throw new NotFoundException("No Technology record found to update");
        if (!isTechnologyBelongedToUser(technologyRequest.getTechnologyId()))
            throw new BadRequestException("You have no such technology");

        Technology technology = technologyRepository.findById(technologyRequest.getTechnologyId()).get();
                /*.orElseThrow(()-> new NotFoundException("No technology with this Id in Repository"));*/

        if (technologyRequest.getTechnologyName()!=null)technology.setTechnologyName(technologyRequest.getTechnologyName());
        technology.setResume(resumeService.getResumeFromAuthentication());

        return TechnologyResponse.of(technologyRepository.save(technology));
    }

    @Override
    public void delete(Long id) {
        if (!isTechnologyBelongedToUser(id)) throw new BadRequestException("You have no such technology");

        /*if (!(technologyRepository.existsById(id)))
            throw new NotFoundException("No Technology found to delete");*/
        technologyRepository.deleteById(id);
    }

    @Override
    public Technology getTechnologyById(Long id) {
        return technologyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Technology is not found"));
    }

    @Override
    public TechnologyResponse getTechnologyResponseById(Long id) {
        return TechnologyResponse.of(getTechnologyById(id));
    }

    @Override
    public List<TechnologyResponse> getAllTechnologiesResponsesInResume(Long resumeId) {

        return technologyRepository.findAllByResumeId(resumeId)
                .stream()
                .map(technology -> TechnologyResponse.of(technology))
                .collect(Collectors.toList());
    }

    private boolean isTechnologyBelongedToUser(Long technologyId){
        long count = resumeService.getResumeFromAuthentication().getTechnologyList()
                .stream()
                .filter(technology -> technology.getId().equals(technologyId)).count();

        return count==1;
    }
}
