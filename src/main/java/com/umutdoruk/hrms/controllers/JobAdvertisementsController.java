package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.concretes.JobAdvertisement;
import com.umutdoruk.hrms.service.services.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobadvertisement")
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

    @GetMapping("/getById")
    public ResponseEntity<JobAdvertisement> getById(@RequestParam int id){
        JobAdvertisement jobAdvertisement = jobAdvertisementService.getById(id);
        return new ResponseEntity<>(jobAdvertisement, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrue")
    public ResponseEntity<List<JobAdvertisement>> findByActiveTrue(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findByActiveTrue();
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueAndCreateDateAsc")
    public ResponseEntity<List<JobAdvertisement>> findByActiveTrueAndCreateDateAsc(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findByActiveTrueAndCreateDateAsc();
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueOrderByCreateDateDesc")
    public ResponseEntity<List<JobAdvertisement>> findByActiveTrueAndCreateDateDesc(){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findByActiveTrueOrderByCreateDateDesc();
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

    @GetMapping("/findByActiveTrueAndEmployerId")
    public ResponseEntity<List<JobAdvertisement>> findByActiveTrueAndEmployer(int employerId){
        List<JobAdvertisement> jobAdvertisementList = jobAdvertisementService.findByActiveTrueAndEmployer(employerId);
        return new ResponseEntity<>(jobAdvertisementList, HttpStatus.OK);
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody JobAdvertisement jobAdvertisement){
         this.jobAdvertisementService.update(jobAdvertisement);
        return HttpStatus.OK;
    }
}