package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.CandidateRequest;
import com.umutdoruk.hrms.DTO.response.CandidateResponse;
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
    public ResponseEntity<List<CandidateResponse>> getAll(){
        return ResponseEntity.ok(candidateService.getAll());
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<CandidateResponse>  getByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(candidateService.findByEmail(email));
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody CandidateRequest candidateRequest){
        candidateService.add(candidateRequest);
        return HttpStatus.CREATED;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        candidateService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody CandidateRequest candidateRequest) {
        candidateService.update(candidateRequest);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getById(@PathVariable("id") Long id){
        CandidateResponse candidateResponse = candidateService.findById(id);
        return new ResponseEntity<>(candidateResponse,HttpStatus.OK);
    }
}
