package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.ResumeRequest;
import com.umutdoruk.hrms.DTO.response.ResumeResponse;
import com.umutdoruk.hrms.entities.Resume;
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

    @PostMapping("/add")
    public HttpStatus add(@RequestBody ResumeRequest resumeRequest){
        resumeService.add(resumeRequest);
        return HttpStatus.CREATED;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeResponse> findById(@PathVariable("id") Long id){
        ResumeResponse resumeResponse = resumeService.findResumeById(id);
        return new ResponseEntity<>(resumeResponse,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ResumeResponse>> getAll() {
        List<ResumeResponse> resumeResponseList = resumeService.getAll();
        return new ResponseEntity<>(resumeResponseList, HttpStatus.OK);
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody ResumeRequest resumeRequest){
        resumeService.update(resumeRequest);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        resumeService.delete(id);
        return HttpStatus.OK;
    }
}