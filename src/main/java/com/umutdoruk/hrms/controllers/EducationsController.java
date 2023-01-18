package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.concretes.Education;
import com.umutdoruk.hrms.service.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationsController {

    private final EducationService educationService;

    @Autowired
    public EducationsController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Education>> getAll(){
        List<Education> educationList = educationService.getAll();
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody Education education){
        educationService.add(education);
        return HttpStatus.CREATED;
    }

    @GetMapping("/findAllByOrderByGraduationDateAsc")
    public ResponseEntity<List<Education>> getAllByOrderByGraduationDateAsc(){
        List<Education> educationList = educationService.findAllByOrderByGraduationDateAsc();
        return new ResponseEntity<>(educationList,HttpStatus.OK);
    }

    @GetMapping("/findAllByOrderByGraduationDateDesc")
    public ResponseEntity<List<Education>> getAllByOrderByGraduationDateDesc(){
        List<Education> educationList = educationService.findAllByOrderByGraduationDateDesc();
        return new ResponseEntity<>(educationList,HttpStatus.OK);
    }
}