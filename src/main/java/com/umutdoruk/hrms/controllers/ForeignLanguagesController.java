package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.ForeignLanguageRequest;
import com.umutdoruk.hrms.DTO.response.ForeignLanguageResponse;
import com.umutdoruk.hrms.service.services.ForeignLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/foreign_languages")
public class ForeignLanguagesController {

    private final ForeignLanguageService foreignLanguageService;

    @Autowired
    public ForeignLanguagesController(ForeignLanguageService foreignLanguageService) {
        this.foreignLanguageService = foreignLanguageService;
    }
    // ROLE_CANDIDATE
    @PostMapping("/create")
    public HttpStatus create (@RequestBody ForeignLanguageRequest foreignLanguageRequest){
        foreignLanguageService.create(foreignLanguageRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_CANDIDATE
    @PutMapping("/update")
    public HttpStatus update(@RequestBody ForeignLanguageRequest foreignLanguageRequest) {
        foreignLanguageService.update(foreignLanguageRequest);
        return HttpStatus.OK;
    }
    // ROLE_CANDIDATE
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        foreignLanguageService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForeignLanguageResponse> getForeignLanguageResponseById (@PathVariable("id") Long id){
        ForeignLanguageResponse foreignLanguageResponse = foreignLanguageService.getForeignLanguageResponseById(id);
        return new ResponseEntity<>(foreignLanguageResponse,HttpStatus.OK);
    }
    // ROLE_CANDIDATE
    @GetMapping("/getAllByResumeId")
    public ResponseEntity<List<ForeignLanguageResponse>> getAllForeignLanguageResponsesInResume (@RequestParam Long resumeId){
        List<ForeignLanguageResponse> foreignLanguageResponsesList = foreignLanguageService.getAllForeignLanguageResponsesInResume(resumeId);
        return new ResponseEntity<>(foreignLanguageResponsesList, HttpStatus.OK);
    }
}
