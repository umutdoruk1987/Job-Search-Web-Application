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

    @PostMapping("/create")
    public HttpStatus create(@RequestBody WorkExperienceRequest workExperienceRequest){
        workExperienceService.create(workExperienceRequest);
        return HttpStatus.CREATED;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody WorkExperienceRequest workExperienceRequest, @RequestParam Long workExperienceId ) {
        workExperienceService.update(workExperienceRequest,workExperienceId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        workExperienceService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkExperienceResponse> getWorkExperienceResponseById (@PathVariable("id") Long id){
        WorkExperienceResponse workExperienceResponse = workExperienceService.getWorkExperienceResponseById(id);
        return new ResponseEntity<>(workExperienceResponse,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WorkExperienceResponse>> getAllWorkExperienceResponsesInResume (@RequestParam Long resumeId){
        List<WorkExperienceResponse> workExperienceResponseList = workExperienceService.getAllWorkExperienceResponsesInResume(resumeId);
        return new ResponseEntity<>(workExperienceResponseList, HttpStatus.OK);
    }

    @GetMapping("/findAllByOrderByEndDateDesc")
    public ResponseEntity<List<WorkExperienceResponse>> getAllWorkExperienceResponsesByOrderByEndDateDesc(){
        List<WorkExperienceResponse> workExperienceResponseList = workExperienceService.getAllWorkExperienceResponsesByOrderByEndDateDesc();
        return new ResponseEntity<>(workExperienceResponseList,HttpStatus.OK);
    }
}

