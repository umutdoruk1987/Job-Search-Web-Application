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
@RequestMapping("/api/jobTitles")
public class JobTitlesController {

    private final JobTitleService jobTitleService;

    @Autowired
    public JobTitlesController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }
    // ROLE_EMPLOYER
    @PostMapping ("/create")
    public HttpStatus create (@RequestBody JobTitleRequest jobTitleRequest){
        jobTitleService.create(jobTitleRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobTitleRequest jobTitleRequest) {
        jobTitleService.update(jobTitleRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobTitleService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTitleResponse> getJobTitleResponseById (@PathVariable("id") Long id){
        JobTitleResponse jobTitleResponse = jobTitleService.getJobTitleResponseById(id);
        return new ResponseEntity<>(jobTitleResponse,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobTitleResponse>> getAllJobTitleResponses (){
        List<JobTitleResponse> jobTitleResponseList = jobTitleService.getAllJobTitleResponses();
        return new ResponseEntity<>(jobTitleResponseList,HttpStatus.OK);
    }
}
