package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.JobAdvertisementRequest;
import com.umutdoruk.hrms.DTO.response.JobAdvertisementResponse;
import com.umutdoruk.hrms.entities.JobAdvertisement;
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

    @PostMapping("/add")
    public HttpStatus add(@RequestBody JobAdvertisementRequest jobAdvertisementRequest){
        jobAdvertisementService.add(jobAdvertisementRequest);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobAdvertisementResponse>> getAll(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.getAll();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobAdvertisementResponse> getById(@PathVariable("id") Long id){
        JobAdvertisementResponse jobAdvertisementResponse = jobAdvertisementService.getById(id);
        return new ResponseEntity<>(jobAdvertisementResponse, HttpStatus.OK);
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody JobAdvertisementRequest jobAdvertisementRequest){
        jobAdvertisementService.update(jobAdvertisementRequest);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobAdvertisementService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/findByActiveTrue")
    public ResponseEntity<List<JobAdvertisementResponse>> findByActiveTrue(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.findByActiveTrue();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueAndCreatedDateAsc")
    public ResponseEntity<List<JobAdvertisementResponse>> findByActiveTrueAndCreateDateAsc(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.findByActiveTrueAndCreatedDateAsc();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueOrderByCreatedDateDesc")
    public ResponseEntity<List<JobAdvertisementResponse>> findByActiveTrueAndCreateDateDesc(){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.findByActiveTrueOrderByCreatedDateDesc();
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueAndEmployerId")
    public ResponseEntity<List<JobAdvertisementResponse>> findByActiveTrueAndEmployer(Long employerId){
        List<JobAdvertisementResponse> jobAdvertisementResponseList = jobAdvertisementService.findByActiveTrueAndEmployer(employerId);
        return new ResponseEntity<>(jobAdvertisementResponseList, HttpStatus.OK);
    }

}