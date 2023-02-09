package com.umutdoruk.jobSearch.controllers;

import com.umutdoruk.jobSearch.dto.request.ResumeRequest;
import com.umutdoruk.jobSearch.dto.response.ResumeResponse;
import com.umutdoruk.jobSearch.service.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumesController {
    private final ResumeService resumeService;

    @Autowired
    public ResumesController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }


    @PutMapping("/update")  // ROLE_CANDIDATE
    public ResponseEntity<ResumeResponse> update(@RequestBody ResumeRequest resumeRequest){
        ResumeResponse resumeResponse = resumeService.update(resumeRequest);
        return new ResponseEntity<>(resumeResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete")    // ROLE_CANDIDATE
    public HttpStatus delete(/*@PathVariable("id") Long id*/){
        resumeService.delete();
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")   // ROLE_CANDIDATE
    public ResponseEntity<ResumeResponse> getResumeResponseById (@PathVariable("id") Long id){
        ResumeResponse resumeResponse = resumeService.getResumeResponseById(id);
        return new ResponseEntity<>(resumeResponse,HttpStatus.OK);
    }

    @GetMapping("/getAll") // ROLE_EMPLOYER
    public ResponseEntity<List<ResumeResponse>> getAllResumeResponses() {
        List<ResumeResponse> resumeResponseList = resumeService.getAllResumeResponses();
        return new ResponseEntity<>(resumeResponseList, HttpStatus.OK);
    }

}