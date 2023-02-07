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
    // ROLE_EMPLOYER
    @PostMapping("/create")
    public HttpStatus create(@RequestBody WorkingTimeRequest workingTimeRequest){
        workingTimeService.create(workingTimeRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/update")
    public HttpStatus update(@RequestBody WorkingTimeRequest workingTimeRequest) {
        workingTimeService.update(workingTimeRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        workingTimeService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingTimeResponse> getWorkingTimeResponseById (@PathVariable("id") Long id){
        WorkingTimeResponse workingTimeResponse = workingTimeService.getWorkingTimeResponseById(id);
        return new ResponseEntity<>(workingTimeResponse,HttpStatus.OK);
    }

    @GetMapping("/{WorkingTimeName}")
    public ResponseEntity<List<WorkingTimeResponse>> getJobPositionResponseByName (@PathVariable("workingTimeName") String workingTimeName){
        List<WorkingTimeResponse> workingTimeResponseList = workingTimeService.getWorkingTimeResponseByName(workingTimeName);
        return new ResponseEntity<>(workingTimeResponseList, HttpStatus.OK);
    }
    // ROLE_CANDIDATE
    @GetMapping("/getAll")
    public ResponseEntity<List<WorkingTimeResponse>> getAllWorkingTimeResponses(){
        List<WorkingTimeResponse> workingTimeResponseList = workingTimeService.getAllWorkingTimeResponses();
        return new ResponseEntity<>(workingTimeResponseList, HttpStatus.OK);
    }
}
