package com.umutdoruk.jobSearch.controllers.candidate;

import com.umutdoruk.jobSearch.dto.candidate.CandidateRequest;
import com.umutdoruk.jobSearch.dto.candidate.CandidateResponse;
import com.umutdoruk.jobSearch.service.candidate.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates/")
public class CandidatesController {

    private final CandidateService candidateService;

    @Autowired
    public CandidatesController (CandidateService candidateService){
        this.candidateService=candidateService;
    }


    @PutMapping() // ROLE_CANDIDATE
    public ResponseEntity<CandidateResponse> update(@RequestBody CandidateRequest candidateRequest) {
        CandidateResponse candidateResponse = candidateService.update(candidateRequest);
        return new ResponseEntity<>(candidateResponse, HttpStatus.OK);
    }

    @GetMapping("get/{id}") //ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<CandidateResponse> getCandidateResponseById(@PathVariable("id") Long id){
        CandidateResponse candidateResponse = candidateService.getCandidateResponseById(id);
        return new ResponseEntity<>(candidateResponse,HttpStatus.OK);
    }

    @GetMapping("get/All")//ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<CandidateResponse>> getAllCandidateResponses(){
        return ResponseEntity.ok(candidateService.getAllCandidateResponses());
    }

     /*
    @PostMapping("/create") // ROLE_CANDIDATE
    public HttpStatus create (@RequestBody CandidateRequest candidateRequest){
        candidateService.create(candidateRequest);
        return HttpStatus.CREATED;
    }*/
}
