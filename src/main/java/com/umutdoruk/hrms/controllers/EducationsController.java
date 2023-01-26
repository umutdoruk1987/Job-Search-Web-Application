package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.EducationRequest;
import com.umutdoruk.hrms.DTO.response.EducationResponse;
import com.umutdoruk.hrms.service.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationsController {

    private final EducationService educationService;

    @Autowired
    public EducationsController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/create")
    public HttpStatus create (@RequestBody EducationRequest educationRequest){
        educationService.create(educationRequest);
        return HttpStatus.CREATED;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody EducationRequest educationRequest, @RequestParam Long educationId ) {
        educationService.update(educationRequest, educationId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        educationService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponse> getEducationResponseById (@PathVariable("id") Long id){
        EducationResponse educationResponse = educationService.getEducationResponseById(id);
        return new ResponseEntity<>(educationResponse,HttpStatus.OK);
    }

    @GetMapping("/getAllByResumeId")
    public ResponseEntity<List<EducationResponse>> getAllEducationResponsesInResume (@RequestParam Long resumeId){
        List<EducationResponse> educationList = educationService.getAllEducationResponsesInResume(resumeId);
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("/findAllByOrderByGraduationDateAsc")
    public ResponseEntity<List<EducationResponse>> getAllByOrderByGraduationDateAsc(){
        List<EducationResponse> educationList = educationService.findAllByOrderByGraduationDateAsc();
        return new ResponseEntity<>(educationList,HttpStatus.OK);
    }

    @GetMapping("/findAllByOrderByGraduationDateDesc")
    public ResponseEntity<List<EducationResponse>> getAllByOrderByGraduationDateDesc(){
        List<EducationResponse> educationList = educationService.findAllByOrderByGraduationDateDesc();
        return new ResponseEntity<>(educationList,HttpStatus.OK);
    }
}