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

    @GetMapping("/getAll")
    public ResponseEntity<List<EducationResponse>> getAll(@RequestParam Long resumeId){
        List<EducationResponse> educationList = educationService.getAll(resumeId);
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody EducationRequest educationRequest){
        educationService.add(educationRequest);
        return HttpStatus.CREATED;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        educationService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody EducationRequest educationRequest, @RequestParam Long educationId ) {
        educationService.update(educationRequest, educationId);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponse> getById(@PathVariable("id") Long id){
        EducationResponse educationResponse = educationService.getById(id);
        return new ResponseEntity<>(educationResponse,HttpStatus.OK);
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