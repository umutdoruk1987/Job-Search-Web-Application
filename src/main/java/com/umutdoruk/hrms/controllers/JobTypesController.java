package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.JobType;
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
    public HttpStatus add(@RequestBody JobType jobType){
        jobTypeService.add(jobType);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobType>> getAll(){
        List<JobType> jobTypeList = jobTypeService.getAll();
        return new ResponseEntity<>(jobTypeList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobType> getById(@PathVariable("id") Long id){
        JobType jobType = jobTypeService.getById(id);
        return new ResponseEntity<>(jobType,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobTypeService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobType jobType) {
        jobTypeService.update(jobType);
        return HttpStatus.OK;
    }
}
