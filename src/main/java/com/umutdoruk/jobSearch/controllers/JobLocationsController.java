package com.umutdoruk.jobSearch.controllers;

import com.umutdoruk.jobSearch.dto.request.JobLocationRequest;
import com.umutdoruk.jobSearch.dto.response.JobLocationResponse;
import com.umutdoruk.jobSearch.service.services.JobLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job_locations")
public class JobLocationsController {

    private final JobLocationService jobLocationService;

    @Autowired
    public JobLocationsController(JobLocationService jobLocationService) {
        this.jobLocationService = jobLocationService;
    }
    // ROLE_EMPLOYER
    @PostMapping("/create")
    public HttpStatus create(@RequestBody JobLocationRequest jobLocationRequest) {
        jobLocationService.create(jobLocationRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobLocationRequest jobLocationRequest) {
        jobLocationService.update(jobLocationRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobLocationService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobLocationResponse> getJobLocationResponseById (@PathVariable("id") Long id){
         JobLocationResponse jobLocationResponse = jobLocationService.getJobLocationResponseById(id);
        return new ResponseEntity<>(jobLocationResponse,HttpStatus.OK);
    }

    //getAllJobAdvertisementInCIty


}
