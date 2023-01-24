package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.JobTypeRequest;
import com.umutdoruk.hrms.DTO.response.JobTypeResponse;
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
    public HttpStatus add(@RequestBody JobTypeRequest jobTypeRequest){
        jobTypeService.add(jobTypeRequest);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobTypeResponse>> getAll(){
        List<JobTypeResponse> jobTypeResponseList = jobTypeService.getAll();
        return new ResponseEntity<>(jobTypeResponseList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTypeResponse> getById(@PathVariable("id") Long id){
        JobTypeResponse jobTypeResponse = jobTypeService.getById(id);
        return new ResponseEntity<>(jobTypeResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobTypeService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobTypeRequest jobTypeRequest) {
        jobTypeService.update(jobTypeRequest);
        return HttpStatus.OK;
    }
}
