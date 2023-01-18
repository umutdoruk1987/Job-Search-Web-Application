package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.concretes.Candidate;
import com.umutdoruk.hrms.service.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {

    private final CandidateService candidateService;

    @Autowired
    public CandidatesController (CandidateService candidateService){
        this.candidateService=candidateService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Candidate>> getAll(){
        return ResponseEntity.ok(this.candidateService.getAll());
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<List<Candidate>>  getByEmail(String email) {
        return ResponseEntity.ok(this.candidateService.findByEmployerEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Candidate candidate){
        this.candidateService.add(candidate);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }



}