package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.concretes.Resume;
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
    public HttpStatus add(@RequestBody Resume resume){
        resumeService.add(resume);
        return HttpStatus.CREATED;
    }

    @GetMapping("/{id}}")
    public ResponseEntity<Resume> findById(@PathVariable int id){
        Resume resume = resumeService.findByResumeId(id);
        return new ResponseEntity<>(resume,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Resume>> getAll() {
        List<Resume> resumeList = resumeService.getAll();
        return new ResponseEntity<>(resumeList, HttpStatus.OK);
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody Resume resume){
        resumeService.update(resume);
        return HttpStatus.OK;
    }

}