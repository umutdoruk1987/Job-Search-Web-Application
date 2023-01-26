package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.TechnologyRequest;
import com.umutdoruk.hrms.DTO.response.TechnologyResponse;
import com.umutdoruk.hrms.service.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/technologies")
public class TechnologiesController {

    private final TechnologyService technologyService;

    @Autowired
    public TechnologiesController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping("/create")
    public HttpStatus create(@RequestBody TechnologyRequest technologyRequest){
        technologyService.create(technologyRequest);
        return HttpStatus.CREATED;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody TechnologyRequest technologyRequest,@RequestParam Long foreignLanguageId){
        technologyService.update(technologyRequest, foreignLanguageId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        technologyService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnologyResponse> getTechnologyResponseById (@PathVariable("id") Long id){
        TechnologyResponse technologyResponse = technologyService.getTechnologyResponseById(id);
        return new ResponseEntity<>(technologyResponse,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TechnologyResponse>> getAllTechnologiesResponsesInResume (@RequestParam Long resumeId){
        List<TechnologyResponse> technologyResponseList = technologyService.getAllTechnologiesResponsesInResume(resumeId);
        return new ResponseEntity<>(technologyResponseList, HttpStatus.OK);
    }
}

