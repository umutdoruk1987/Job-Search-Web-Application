package com.umutdoruk.jobSearch.controllers.employer;

import com.umutdoruk.jobSearch.dto.employer.JobLocationRequest;
import com.umutdoruk.jobSearch.dto.employer.JobLocationResponse;
import com.umutdoruk.jobSearch.service.employer.JobLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job_locations/")
public class JobLocationsController {

    private final JobLocationService jobLocationService;

    @Autowired
    public JobLocationsController(JobLocationService jobLocationService) {
        this.jobLocationService = jobLocationService;
    }

    @PostMapping() // ROLE_EMPLOYER
    public ResponseEntity<JobLocationResponse> create(@RequestBody JobLocationRequest jobLocationRequest) {
        JobLocationResponse jobLocationResponse = jobLocationService.create(jobLocationRequest);
        return new ResponseEntity<>(jobLocationResponse,HttpStatus.CREATED);
    }

    @PutMapping() // ROLE_EMPLOYER
    public ResponseEntity<JobLocationResponse> update(@RequestBody JobLocationRequest jobLocationRequest) {
        JobLocationResponse jobLocationResponse = jobLocationService.update(jobLocationRequest);
        return new ResponseEntity<>(jobLocationResponse,HttpStatus.OK);
    }

    @DeleteMapping("{id}") // ROLE_EMPLOYER
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobLocationService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("{id}")   // ROLE_EMPLOYER
    public ResponseEntity<JobLocationResponse> getJobLocationResponseById (@PathVariable("id") Long id){
         JobLocationResponse jobLocationResponse = jobLocationService.getJobLocationResponseById(id);
        return new ResponseEntity<>(jobLocationResponse,HttpStatus.OK);
    }

    //getAllJobAdvertisementInCIty


}
