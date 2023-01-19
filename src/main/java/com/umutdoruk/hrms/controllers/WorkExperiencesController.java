package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.concretes.WorkExperience;
import com.umutdoruk.hrms.service.services.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/work_experiences")
public class WorkExperiencesController {

    private final WorkExperienceService workExperienceService;

    @Autowired
    public WorkExperiencesController(WorkExperienceService workExperienceService) {
        this.workExperienceService = workExperienceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WorkExperience>> getAll(){
        List<WorkExperience> workExperienceList = workExperienceService.getAll();
        return new ResponseEntity<>(workExperienceList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody WorkExperience workExperience){
        workExperienceService.add(workExperience);
        return HttpStatus.CREATED;
    }

    @GetMapping("/findAllByOrderByEndDateDesc")
    public ResponseEntity<List<WorkExperience>> findAllByOrderByEndDateDesc(){
        List<WorkExperience> workExperienceList = workExperienceService.findAllByOrder();
        return new ResponseEntity<>(workExperienceList,HttpStatus.OK);
    }
}

