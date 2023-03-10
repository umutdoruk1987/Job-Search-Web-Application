package com.umutdoruk.jobSearch.controllers.candidate;

import com.umutdoruk.jobSearch.dto.candidate.ForeignLanguageRequest;
import com.umutdoruk.jobSearch.dto.candidate.ForeignLanguageResponse;
import com.umutdoruk.jobSearch.service.candidate.ForeignLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/foreign_languages/")
public class ForeignLanguagesController {

    private final ForeignLanguageService foreignLanguageService;

    @Autowired
    public ForeignLanguagesController(ForeignLanguageService foreignLanguageService) {
        this.foreignLanguageService = foreignLanguageService;
    }

    @PostMapping()  // ROLE_CANDIDATE
    public ResponseEntity<ForeignLanguageResponse> create (@RequestBody ForeignLanguageRequest foreignLanguageRequest){
        ForeignLanguageResponse foreignLanguageResponse = foreignLanguageService.create(foreignLanguageRequest);
        return new ResponseEntity<>(foreignLanguageResponse,HttpStatus.CREATED);
    }

    @PutMapping()  // ROLE_CANDIDATE
    public ResponseEntity<ForeignLanguageResponse> update(@RequestBody ForeignLanguageRequest foreignLanguageRequest) {
        ForeignLanguageResponse foreignLanguageResponse =foreignLanguageService.update(foreignLanguageRequest);
        return new ResponseEntity<>(foreignLanguageResponse,HttpStatus.OK);
    }

    @DeleteMapping("{id}") // ROLE_CANDIDATE
    public HttpStatus delete(@PathVariable("id") Long id) {
        foreignLanguageService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("{id}") // ROLE_CANDIDATE
    public ResponseEntity<ForeignLanguageResponse> getForeignLanguageResponseById (@PathVariable("id") Long id){
        ForeignLanguageResponse foreignLanguageResponse = foreignLanguageService.getForeignLanguageResponseById(id);
        return new ResponseEntity<>(foreignLanguageResponse,HttpStatus.OK);
    }

    @GetMapping("getAllByResumeId") // ROLE_CANDIDATE
    public ResponseEntity<List<ForeignLanguageResponse>> getAllForeignLanguageResponsesInResume (@RequestParam Long resumeId){
        List<ForeignLanguageResponse> foreignLanguageResponsesList = foreignLanguageService.getAllForeignLanguageResponsesInResume(resumeId);
        return new ResponseEntity<>(foreignLanguageResponsesList, HttpStatus.OK);
    }
}
