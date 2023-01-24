package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.TechnologyRequest;
import com.umutdoruk.hrms.DTO.response.TechnologyResponse;
import com.umutdoruk.hrms.entities.Technology;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<TechnologyResponse>> getAll(){
        List<TechnologyResponse> technologyResponseList = technologyService.getAll();
        return new ResponseEntity<>(technologyResponseList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody TechnologyRequest technologyRequest){
        technologyService.add(technologyRequest);
        return HttpStatus.CREATED;
    }

    @GetMapping("/{id}")
    public HttpStatus findById(@PathVariable("id") Long id){
        technologyService.findById(id);
        return HttpStatus.OK;
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody TechnologyRequest technologyRequest){
        technologyService.update(technologyRequest);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        technologyService.delete(id);
        return HttpStatus.OK;
    }
}

