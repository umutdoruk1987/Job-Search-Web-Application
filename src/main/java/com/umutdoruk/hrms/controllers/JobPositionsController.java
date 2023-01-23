package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.JobPosition;
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
    public ResponseEntity<List<JobPosition>> getAll(){
        List<JobPosition> jobPositionList = jobPositionService.getAll();
        return new ResponseEntity<>(jobPositionList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody JobPosition jobPosition){
        jobPositionService.add(jobPosition);
        return HttpStatus.CREATED;
    }

    @GetMapping("/{jobPositionName}")
    public ResponseEntity<List<JobPosition>> getByName(@PathVariable("jobPositionName") String jobPositionName){
        List<JobPosition> jobPositionList = jobPositionService.findByName(jobPositionName);
        return new ResponseEntity<>(jobPositionList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobPositionService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobPosition jobPosition) {
        jobPositionService.update(jobPosition);
        return HttpStatus.OK;
    }
}
