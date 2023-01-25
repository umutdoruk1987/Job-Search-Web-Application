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

    @GetMapping("/getAll")
    public ResponseEntity<List<JobPositionResponse>> getAll(){
        List<JobPositionResponse> jobPositionResponseList = jobPositionService.getAll();
        return new ResponseEntity<>(jobPositionResponseList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody JobPositionRequest jobPositionRequest){
        jobPositionService.add(jobPositionRequest);
        return HttpStatus.CREATED;
    }

    @GetMapping("/{jobPositionName}")
    public ResponseEntity<List<JobPositionResponse>> getByName(@PathVariable("jobPositionName") String jobPositionName){
        List<JobPositionResponse> jobPositionResponseList = jobPositionService.findByName(jobPositionName);
        return new ResponseEntity<>(jobPositionResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPositionResponse> getById (@PathVariable("id") Long id){
        JobPositionResponse jobPositionResponse = jobPositionService.findById(id);
        return new ResponseEntity<>(jobPositionResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobPositionService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobPositionRequest jobPositionRequest, @RequestParam Long jobPositionId) {
        jobPositionService.update(jobPositionRequest, jobPositionId);
        return HttpStatus.OK;
    }
}
