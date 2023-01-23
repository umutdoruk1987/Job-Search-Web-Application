package com.umutdoruk.hrms.controllers;

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
    public ResponseEntity<List<Technology>> getAll(){
        List<Technology> technologyList = technologyService.getAll();
        return new ResponseEntity<>(technologyList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody Technology technology){
        technologyService.add(technology);
        return HttpStatus.CREATED;
    }

    @GetMapping("/{id}")
    public HttpStatus findById(@PathVariable("id") Long id){
        technologyService.findById(id);
        return HttpStatus.OK;
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody Technology technology){
        technologyService.update(technology);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        technologyService.delete(id);
        return HttpStatus.OK;
    }
}

