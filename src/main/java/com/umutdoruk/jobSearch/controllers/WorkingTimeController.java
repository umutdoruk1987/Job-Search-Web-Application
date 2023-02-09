package com.umutdoruk.jobSearch.controllers;

import com.umutdoruk.jobSearch.dto.request.WorkingTimeRequest;
import com.umutdoruk.jobSearch.dto.response.WorkingTimeResponse;
import com.umutdoruk.jobSearch.service.services.WorkingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/working_time")
public class WorkingTimeController {

    private final WorkingTimeService workingTimeService;

    @Autowired
    public WorkingTimeController(WorkingTimeService workingTimeService) {
        this.workingTimeService = workingTimeService;
    }

    @PostMapping("/create") // ROLE_EMPLOYER
    public ResponseEntity<WorkingTimeResponse> create(@RequestBody WorkingTimeRequest workingTimeRequest){
        WorkingTimeResponse workingTimeResponse = workingTimeService.create(workingTimeRequest);
        return new ResponseEntity<>(workingTimeResponse,HttpStatus.CREATED);
    }

    @PutMapping("/update") // ROLE_EMPLOYER
    public ResponseEntity<WorkingTimeResponse> update(@RequestBody WorkingTimeRequest workingTimeRequest) {
        WorkingTimeResponse workingTimeResponse = workingTimeService.update(workingTimeRequest);
        return new ResponseEntity<>(workingTimeResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")  // ROLE_EMPLOYER
    public HttpStatus delete(@PathVariable("id") Long id) {
        workingTimeService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/id/{id}") // ROLE_EMPLOYER
    public ResponseEntity<WorkingTimeResponse> getWorkingTimeResponseById (@PathVariable("id") Long id){
        WorkingTimeResponse workingTimeResponse = workingTimeService.getWorkingTimeResponseById(id);
        return new ResponseEntity<>(workingTimeResponse,HttpStatus.OK);
    }

    @GetMapping("/name/{WorkingTimeName}")  // ROLE_EMPLOYER
    public ResponseEntity<List<WorkingTimeResponse>> getWorkingTimeResponseByName (@PathVariable("workingTimeName") String workingTimeName){
        List<WorkingTimeResponse> workingTimeResponseList = workingTimeService.getWorkingTimeResponseByName(workingTimeName);
        return new ResponseEntity<>(workingTimeResponseList, HttpStatus.OK);
    }

    @GetMapping("/getAll") // ROLE_EMPLOYER
    public ResponseEntity<List<WorkingTimeResponse>> getAllWorkingTimeResponses(){
        List<WorkingTimeResponse> workingTimeResponseList = workingTimeService.getAllWorkingTimeResponses();
        return new ResponseEntity<>(workingTimeResponseList, HttpStatus.OK);
    }
}
