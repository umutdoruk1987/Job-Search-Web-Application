package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.JobTypeRequest;
import com.umutdoruk.hrms.DTO.response.JobTypeResponse;
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
    // ROLE_EMPLOYER
    @PostMapping ("/create")
    public HttpStatus create (@RequestBody JobTypeRequest jobTypeRequest){
        jobTypeService.create(jobTypeRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobTypeRequest jobTypeRequest) {
        jobTypeService.update(jobTypeRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobTypeService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTypeResponse> getJobTypeResponseById (@PathVariable("id") Long id){
        JobTypeResponse jobTypeResponse = jobTypeService.getJobTypeResponseById(id);
        return new ResponseEntity<>(jobTypeResponse,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobTypeResponse>> getAllJobTypeResponses (){
        List<JobTypeResponse> jobTypeResponseList = jobTypeService.getAllJobTypeResponses();
        return new ResponseEntity<>(jobTypeResponseList,HttpStatus.OK);
    }
}
