package com.umutdoruk.jobSearch.controllers.employer;

import com.umutdoruk.jobSearch.dto.employer.JobAdvertisementRequest;
import com.umutdoruk.jobSearch.dto.employer.JobAdvertisementResponse;
import com.umutdoruk.jobSearch.service.employer.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/job_advertisement/")
public class JobAdvertisementsController {

    private final JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @PostMapping()    // ROLE_EMPLOYER
    public ResponseEntity<JobAdvertisementResponse> create(@RequestBody JobAdvertisementRequest jobAdvertisementRequest){
        JobAdvertisementResponse jobAdvertisementResponse = jobAdvertisementService.create(jobAdvertisementRequest);
        return new ResponseEntity<>(jobAdvertisementResponse, HttpStatus.CREATED);
    }

    @PutMapping()  // ROLE_EMPLOYER
    public ResponseEntity<JobAdvertisementResponse> update(@RequestBody JobAdvertisementRequest jobAdvertisementRequest){
        JobAdvertisementResponse jobAdvertisementResponse = jobAdvertisementService.update(jobAdvertisementRequest);
        return new ResponseEntity<>(jobAdvertisementResponse,HttpStatus.OK);
    }

    @DeleteMapping("{id}") // ROLE_EMPLOYER
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobAdvertisementService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("get/{id}") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<JobAdvertisementResponse> getJobAdvertisementResponseById (@PathVariable("id") Long id){
        JobAdvertisementResponse jobAdvertisementResponse = jobAdvertisementService.getJobAdvertisementResponseById(id);
        return new ResponseEntity<>(jobAdvertisementResponse, HttpStatus.OK);
    }

    @GetMapping("get/All") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisements(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisements();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("get/AllByActiveTrue")  // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrue(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrue();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("get/AllByActiveTrueAndCreatedDateAsc") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueAndCreatedDateAsc();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("get/AllByActiveTrueOrderByCreatedDateDesc") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueOrderByCreatedDateDesc();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("get/AllByActiveTrueAndEmployerId") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByActiveTrueAndEmployerId(@RequestParam Long employerId){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByActiveTrueAndEmployerId(employerId);
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("get/AllByEmployerId") // ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<JobAdvertisementResponse>> getAllJobAdvertisementsByEmployerId(@RequestParam Long employerId){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAllJobAdvertisementsByEmployerId(employerId);
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }
}