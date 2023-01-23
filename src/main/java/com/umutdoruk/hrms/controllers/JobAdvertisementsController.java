package com.umutdoruk.hrms.controllers;

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
    public HttpStatus add(@RequestBody JobAdvertisement jobAdvertisement){
        jobAdvertisementService.add(jobAdvertisement);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobAdvertisement>> getAll(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.getAll();
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobAdvertisement> getById(@PathVariable("id") Long id){
        JobAdvertisement jobAdvertisement = jobAdvertisementService.getById(id);
        return new ResponseEntity<>(jobAdvertisement, HttpStatus.OK);
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody JobAdvertisement jobAdvertisement){
        jobAdvertisementService.update(jobAdvertisement);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        jobAdvertisementService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/findByActiveTrue")
    public ResponseEntity<List<JobAdvertisement>> findByActiveTrue(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findByActiveTrue();
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueAndCreatedDateAsc")
    public ResponseEntity<List<JobAdvertisement>> findByActiveTrueAndCreateDateAsc(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findByActiveTrueAndCreatedDateAsc();
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueOrderByCreatedDateDesc")
    public ResponseEntity<List<JobAdvertisement>> findByActiveTrueAndCreateDateDesc(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findByActiveTrueOrderByCreatedDateDesc();
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueAndEmployerId")
    public ResponseEntity<List<JobAdvertisement>> findByActiveTrueAndEmployer(Long employerId){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findByActiveTrueAndEmployer(employerId);
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

}