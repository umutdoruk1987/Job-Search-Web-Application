package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.Candidate;
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
    public ResponseEntity<Candidate>  getByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(candidateService.findByEmail(email));
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody Candidate candidate){
        candidateService.add(candidate);
        return HttpStatus.CREATED;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        candidateService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody Candidate candidate) {
        candidateService.update(candidate);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getById(@PathVariable("id") Long id){
        Candidate candidate = candidateService.findById(id);
        return new ResponseEntity<>(candidate,HttpStatus.OK);
    }
}
