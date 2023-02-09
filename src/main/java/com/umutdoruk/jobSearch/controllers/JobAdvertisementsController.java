package com.umutdoruk.jobSearch.controllers;

import com.umutdoruk.jobSearch.dto.request.JobAdvertisementRequest;
import com.umutdoruk.jobSearch.dto.response.JobAdvertisementResponse;
import com.umutdoruk.jobSearch.service.services.JobAdvertisementService;
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

    @PostMapping("/create")    // ROLE_EMPLOYER
    public ResponseEntity<JobAdvertisementResponse> create(@RequestBody JobAdvertisementRequest jobAdvertisementRequest){
        JobAdvertisementResponse jobAdvertisementResponse = jobAdvertisementService.create(jobAdvertisementRequest);
        return new ResponseEntity<>(jobAdvertisementResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update")  // ROLE_EMPLOYER
    public ResponseEntity<JobAdvertisementResponse> update(@RequestBody JobAdvertisementRequest jobAdvertisementRequest){
        JobAdvertisementResponse jobAdvertisementResponse = jobAdvertisementService.update(jobAdvertisementRequest);
        return new ResponseEntity<>(jobAdvertisementResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}") // ROLE_EMPLOYER
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobAdvertisementService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<JobAdvertisementResponse> getJobAdvertisementResponseById (@PathVariable("id") Long id){
        JobAdvertisementResponse jobAdvertisementResponse = jobAdvertisementService.getJobAdvertisementResponseById(id);
        return new ResponseEntity<>(jobAdvertisementResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisements(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisements();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("/getAllByActiveTrue")  // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrue(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrue();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("/getAllByActiveTrueAndCreatedDateAsc") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("/getAllByActiveTrueOrderByCreatedDateDesc") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("/getAllByActiveTrueAndEmployerId") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueAndEmployerId(@RequestParam Long employerId){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueAndEmployerId(employerId);
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("getAllByEmployerId") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByEmployerId(@RequestParam Long employerId){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employerId);
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }
}