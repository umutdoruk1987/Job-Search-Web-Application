package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.concretes.JobType;
import com.umutdoruk.hrms.service.services.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobTypes")
public class JobTypesController {

    private final JobTypeService jobTypeService;

    @Autowired
    public JobTypesController(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    @PostMapping
    public HttpStatus create(@RequestBody JobType jobType){
        jobTypeService.add(jobType);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobType>> getAll(){
        List<JobType> jobTypeList = jobTypeService.getAll();
        return new ResponseEntity<>(jobTypeList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobType> getById(@PathVariable int id){
        JobType jobType = jobTypeService.getById(id);
        return new ResponseEntity<>(jobType,HttpStatus.OK);
    }

}
