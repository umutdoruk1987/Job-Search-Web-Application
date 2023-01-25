package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.WorkExperienceRequest;
import com.umutdoruk.hrms.DTO.response.WorkExperienceResponse;
import com.umutdoruk.hrms.service.services.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/work_experiences")
public class WorkExperiencesController {

    private final WorkExperienceService workExperienceService;

    @Autowired
    public WorkExperiencesController(WorkExperienceService workExperienceService) {
        this.workExperienceService = workExperienceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WorkExperienceResponse>> getAll(@RequestParam Long resumeId){
        List<WorkExperienceResponse> workExperienceResponseList = workExperienceService.getAll(resumeId);
        return new ResponseEntity<>(workExperienceResponseList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody WorkExperienceRequest workExperienceRequest){
        workExperienceService.add(workExperienceRequest);
        return HttpStatus.CREATED;
    }

    @GetMapping("/findAllByOrderByEndDateDesc")
    public ResponseEntity<List<WorkExperienceResponse>> findAllByOrderByEndDateDesc(){
        List<WorkExperienceResponse> workExperienceResponseList = workExperienceService.findAllByOrderByEndDateDesc();
        return new ResponseEntity<>(workExperienceResponseList,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        workExperienceService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody WorkExperienceRequest workExperienceRequest, @RequestParam Long workExperienceId ) {
        workExperienceService.update(workExperienceRequest,workExperienceId);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkExperienceResponse> getById(@PathVariable("id") Long id){
        WorkExperienceResponse workExperienceResponse = workExperienceService.getById(id);
        return new ResponseEntity<>(workExperienceResponse,HttpStatus.OK);
    }
}

