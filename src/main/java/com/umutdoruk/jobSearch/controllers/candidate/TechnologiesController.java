package com.umutdoruk.jobSearch.controllers.candidate;

import com.umutdoruk.jobSearch.dto.candidate.TechnologyRequest;
import com.umutdoruk.jobSearch.dto.candidate.TechnologyResponse;
import com.umutdoruk.jobSearch.service.candidate.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/technologies/")
public class TechnologiesController {

    private final TechnologyService technologyService;

    @Autowired
    public TechnologiesController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping() // ROLE_CANDIDATE
    public ResponseEntity<TechnologyResponse> create(@RequestBody TechnologyRequest technologyRequest){
        TechnologyResponse technologyResponse = technologyService.create(technologyRequest);
        return new ResponseEntity<>(technologyResponse,HttpStatus.CREATED);
    }

    @PutMapping() // ROLE_CANDIDATE
    public ResponseEntity<TechnologyResponse> update(@RequestBody TechnologyRequest technologyRequest){
        TechnologyResponse technologyResponse = technologyService.update(technologyRequest);
        return new ResponseEntity<>(technologyResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}") // ROLE_CANDIDATE
    public HttpStatus delete(@PathVariable("id") Long id){
        technologyService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("{id}") // ROLE_CANDIDATE
    public ResponseEntity<TechnologyResponse> getTechnologyResponseById (@PathVariable("id") Long id){
        TechnologyResponse technologyResponse = technologyService.getTechnologyResponseById(id);
        return new ResponseEntity<>(technologyResponse,HttpStatus.OK);
    }

    @GetMapping("getAllByResumeId") // ROLE_CANDIDATE
    public ResponseEntity<List<TechnologyResponse>> getAllTechnologiesResponsesInResume (@RequestParam Long resumeId){
        List<TechnologyResponse> technologyResponseList = technologyService.getAllTechnologiesResponsesInResume(resumeId);
        return new ResponseEntity<>(technologyResponseList, HttpStatus.OK);
    }
}

