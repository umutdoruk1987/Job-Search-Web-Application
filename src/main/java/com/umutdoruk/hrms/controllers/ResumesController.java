package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.ResumeRequest;
import com.umutdoruk.hrms.DTO.response.ResumeResponse;
import com.umutdoruk.hrms.service.services.ResumeService;
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

    // ROLE_CANDIDATE
    @PutMapping("/update")
    public HttpStatus update(@RequestBody ResumeRequest resumeRequest){
        resumeService.update(resumeRequest);
        return HttpStatus.OK;
    }
    // ROLE_CANDIDATE
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        resumeService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeResponse> getResumeResponseById (@PathVariable("id") Long id){
        ResumeResponse resumeResponse = resumeService.getResumeResponseById(id);
        return new ResponseEntity<>(resumeResponse,HttpStatus.OK);
    }
    // ROLE_EMPLOYER
    @GetMapping("/getAll")
    public ResponseEntity<List<ResumeResponse>> getAllResumeResponses() {
        List<ResumeResponse> resumeResponseList = resumeService.getAllResumeResponses();
        return new ResponseEntity<>(resumeResponseList, HttpStatus.OK);
    }
}