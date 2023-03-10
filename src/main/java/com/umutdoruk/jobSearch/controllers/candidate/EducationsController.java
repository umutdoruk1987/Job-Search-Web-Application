package com.umutdoruk.jobSearch.controllers.candidate;

import com.umutdoruk.jobSearch.dto.candidate.EducationRequest;
import com.umutdoruk.jobSearch.dto.candidate.EducationResponse;
import com.umutdoruk.jobSearch.service.candidate.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educations/")
public class EducationsController {

    private final EducationService educationService;

    @Autowired
    public EducationsController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping() // ROLE_CANDIDATE
    public ResponseEntity<EducationResponse> create (@RequestBody EducationRequest educationRequest){
        EducationResponse educationResponse = educationService.create(educationRequest);
        return new ResponseEntity<>(educationResponse, HttpStatus.CREATED);
    }

    @PutMapping() // ROLE_CANDIDATE
    public ResponseEntity<EducationResponse> update(@RequestBody EducationRequest educationRequest) {
        EducationResponse educationResponse =educationService.update(educationRequest);
        return new ResponseEntity<>(educationResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}") // ROLE_CANDIDATE
    public HttpStatus delete(@PathVariable("id") Long id) {
        educationService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("{id}") // ROLE_CANDIDATE
    public ResponseEntity<EducationResponse> getEducationResponseById (@PathVariable("id") Long id){
        EducationResponse educationResponse = educationService.getEducationResponseById(id);
        return new ResponseEntity<>(educationResponse,HttpStatus.OK);
    }

    @GetMapping("getAllByResumeId")  // ROLE_CANDIDATE
    public ResponseEntity<List<EducationResponse>> getAllEducationResponsesInResume (@RequestParam Long resumeId){
        List<EducationResponse> educationList = educationService.getAllEducationResponsesInResume(resumeId);
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("findAllByOrderByGraduationDateAsc") // ROLE_CANDIDATE
    public ResponseEntity<List<EducationResponse>> getAllByOrderByGraduationDateAsc(){
        List<EducationResponse> educationList = educationService.findAllByOrderByGraduationDateAsc();
        return new ResponseEntity<>(educationList,HttpStatus.OK);
    }

    @GetMapping("findAllByOrderByGraduationDateDesc") // ROLE_CANDIDATE
    public ResponseEntity<List<EducationResponse>> getAllByOrderByGraduationDateDesc(){
        List<EducationResponse> educationList = educationService.findAllByOrderByGraduationDateDesc();
        return new ResponseEntity<>(educationList,HttpStatus.OK);
    }
}