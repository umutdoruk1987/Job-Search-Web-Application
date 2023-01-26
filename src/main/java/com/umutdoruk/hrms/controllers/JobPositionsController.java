package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.JobPositionRequest;
import com.umutdoruk.hrms.DTO.response.JobPositionResponse;
import com.umutdoruk.hrms.service.services.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job_positions")
public class JobPositionsController {

    private final JobPositionService jobPositionService;

    @Autowired
    public JobPositionsController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @PostMapping("/create")
    public HttpStatus create(@RequestBody JobPositionRequest jobPositionRequest){
        jobPositionService.create(jobPositionRequest);
        return HttpStatus.CREATED;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobPositionRequest jobPositionRequest, @RequestParam Long jobPositionId) {
        jobPositionService.update(jobPositionRequest, jobPositionId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobPositionService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPositionResponse> getJobPositionResponseById (@PathVariable("id") Long id){
        JobPositionResponse jobPositionResponse = jobPositionService.getJobPositionResponseById(id);
        return new ResponseEntity<>(jobPositionResponse,HttpStatus.OK);
    }

    @GetMapping("/{jobPositionName}")
    public ResponseEntity<List<JobPositionResponse>> getJobPositionResponseByName (@PathVariable("jobPositionName") String jobPositionName){
        List<JobPositionResponse> jobPositionResponseList = jobPositionService.getJobPositionResponseByName(jobPositionName);
        return new ResponseEntity<>(jobPositionResponseList, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobPositionResponse>> getAllJobPositionResponses(){
        List<JobPositionResponse> jobPositionResponseList = jobPositionService.getAllJobPositionResponses();
        return new ResponseEntity<>(jobPositionResponseList, HttpStatus.OK);
    }
}
