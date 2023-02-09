package com.umutdoruk.jobSearch.controllers;

import com.umutdoruk.jobSearch.dto.request.JobTitleRequest;
import com.umutdoruk.jobSearch.dto.response.JobTitleResponse;
import com.umutdoruk.jobSearch.service.services.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job_titles")
public class JobTitlesController {

    private final JobTitleService jobTitleService;

    @Autowired
    public JobTitlesController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    @PostMapping ("/create") // ROLE_EMPLOYER
    public ResponseEntity<JobTitleResponse> create (@RequestBody JobTitleRequest jobTitleRequest){
        JobTitleResponse jobTitleResponse = jobTitleService.create(jobTitleRequest);
        return new ResponseEntity<>(jobTitleResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update") // ROLE_EMPLOYER
    public ResponseEntity<JobTitleResponse> update(@RequestBody JobTitleRequest jobTitleRequest) {
        JobTitleResponse jobTitleResponse =jobTitleService.update(jobTitleRequest);
        return new ResponseEntity<>(jobTitleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // ROLE_EMPLOYER
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobTitleService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}") // ROLE_EMPLOYER
    public ResponseEntity<JobTitleResponse> getJobTitleResponseById (@PathVariable("id") Long id){
        JobTitleResponse jobTitleResponse = jobTitleService.getJobTitleResponseById(id);
        return new ResponseEntity<>(jobTitleResponse,HttpStatus.OK);
    }

    @GetMapping("/getAll") // ROLE_EMPLOYER
    public ResponseEntity<List<JobTitleResponse>> getAllJobTitleResponses (){
        List<JobTitleResponse> jobTitleResponseList = jobTitleService.getAllJobTitleResponses();
        return new ResponseEntity<>(jobTitleResponseList,HttpStatus.OK);
    }
}
