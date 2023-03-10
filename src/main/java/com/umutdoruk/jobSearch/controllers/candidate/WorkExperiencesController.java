package com.umutdoruk.jobSearch.controllers.candidate;

import com.umutdoruk.jobSearch.dto.candidate.WorkExperienceRequest;
import com.umutdoruk.jobSearch.dto.candidate.WorkExperienceResponse;
import com.umutdoruk.jobSearch.service.candidate.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/work_experiences/")
public class WorkExperiencesController {

    private final WorkExperienceService workExperienceService;

    @Autowired
    public WorkExperiencesController(WorkExperienceService workExperienceService) {
        this.workExperienceService = workExperienceService;
    }

    @PostMapping() // ROLE_CANDIDATE
    public ResponseEntity<WorkExperienceResponse> create(@RequestBody WorkExperienceRequest workExperienceRequest){
        WorkExperienceResponse workExperienceResponse = workExperienceService.create(workExperienceRequest);
        return new ResponseEntity<>(workExperienceResponse,HttpStatus.CREATED);
    }

    @PutMapping() // ROLE_CANDIDATE
    public ResponseEntity<WorkExperienceResponse> update(@RequestBody WorkExperienceRequest workExperienceRequest) {
        WorkExperienceResponse workExperienceResponse = workExperienceService.update(workExperienceRequest);
        return new ResponseEntity<>(workExperienceResponse,HttpStatus.OK);
    }

    @DeleteMapping("{id}")  // ROLE_CANDIDATE
    public HttpStatus delete(@PathVariable("id") Long id) {
        workExperienceService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("{id}") // ROLE_CANDIDATE
    public ResponseEntity<WorkExperienceResponse> getWorkExperienceResponseById (@PathVariable("id") Long id){
        WorkExperienceResponse workExperienceResponse = workExperienceService.getWorkExperienceResponseById(id);
        return new ResponseEntity<>(workExperienceResponse,HttpStatus.OK);
    }

    @GetMapping("getAllByResumeId") // ROLE_CANDIDATE
    public ResponseEntity<List<WorkExperienceResponse>> getAllWorkExperienceResponsesInResume (@RequestParam Long resumeId){
        List<WorkExperienceResponse> workExperienceResponseList = workExperienceService.getAllWorkExperienceResponsesInResume(resumeId);
        return new ResponseEntity<>(workExperienceResponseList, HttpStatus.OK);
    }

    @GetMapping("findAllByOrderByEndDateDesc")  // ROLE_CANDIDATE
    public ResponseEntity<List<WorkExperienceResponse>> getAllWorkExperienceResponsesByOrderByEndDateDesc(){
        List<WorkExperienceResponse> workExperienceResponseList = workExperienceService.getAllWorkExperienceResponsesByOrderByEndDateDesc();
        return new ResponseEntity<>(workExperienceResponseList,HttpStatus.OK);
    }
}

