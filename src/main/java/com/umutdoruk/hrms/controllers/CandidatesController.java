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

    // ROLE_CANDIDATE
    @PostMapping("/create")
    public HttpStatus create (@RequestBody CandidateRequest candidateRequest){
        candidateService.create(candidateRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_CANDIDATE
    @PutMapping("/update")
    public HttpStatus update(@RequestBody CandidateRequest candidateRequest) {
        candidateService.update(candidateRequest);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidateResponseById(@PathVariable("id") Long id){
        CandidateResponse candidateResponse = candidateService.getCandidateResponseById(id);
        return new ResponseEntity<>(candidateResponse,HttpStatus.OK);
    }

    /*@GetMapping("/getByEmail")
    public ResponseEntity<CandidateResponse>  getCandidateResponseByEmail (@PathVariable("email") String email) {
        return ResponseEntity.ok(candidateService.getCandidateResponseByEmail(email));
    }*/
    // ROLE_EMPLOYER
    @GetMapping("/getAll")
    public ResponseEntity<List<CandidateResponse>> getAllCandidateResponses(){
        return ResponseEntity.ok(candidateService.getAllCandidateResponses());
    }
}
