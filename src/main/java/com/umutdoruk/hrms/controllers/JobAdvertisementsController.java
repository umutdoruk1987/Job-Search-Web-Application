package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.JobAdvertisementRequest;
import com.umutdoruk.hrms.DTO.response.JobAdvertisementResponse;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/job_advertisement")
public class JobAdvertisementsController {

    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }
    // ROLE_EMPLOYER
    @PostMapping("/create")
    public HttpStatus create(@RequestBody JobAdvertisementRequest jobAdvertisementRequest){
        jobAdvertisementService.create(jobAdvertisementRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/update")
    public HttpStatus update(@RequestBody JobAdvertisementRequest jobAdvertisementRequest){
        jobAdvertisementService.update(jobAdvertisementRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobAdvertisementService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobAdvertisementResponse> getJobAdvertisementResponseById (@PathVariable("id") Long id){
        JobAdvertisementResponse jobAdvertisementResponse = jobAdvertisementService.getJobAdvertisementResponseById(id);
        return new ResponseEntity<>(jobAdvertisementResponse, HttpStatus.OK);
    }
    // ROLE_CANDIDATE
    @GetMapping("/getAll")
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisements(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisements();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }
    // ROLE_CANDIDATE
    @GetMapping("/getAllByActiveTrue")
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrue(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrue();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }
    // ROLE_CANDIDATE
    @GetMapping("/getAllByActiveTrueAndCreatedDateAsc")
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }
    // ROLE_CANDIDATE
    @GetMapping("/getAllByActiveTrueOrderByCreatedDateDesc")
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }
    // ROLE_CANDIDATE
    @GetMapping("/getAllByActiveTrueAndEmployerId")
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueAndEmployerId(@RequestParam Long employerId){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueAndEmployerId(employerId);
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("getAllByEmployerId")
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByEmployerId(@RequestParam Long employerId){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employerId);
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

}