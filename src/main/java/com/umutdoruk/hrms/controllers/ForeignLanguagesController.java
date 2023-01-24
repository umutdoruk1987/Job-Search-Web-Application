package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.ForeignLanguageRequest;
import com.umutdoruk.hrms.DTO.response.ForeignLanguageResponse;
import com.umutdoruk.hrms.entities.ForeignLanguage;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<ForeignLanguageResponse>> getAll(){
        List<ForeignLanguageResponse> foreignLanguageResponsesList = foreignLanguageService.getAll();
        return new ResponseEntity<>(foreignLanguageResponsesList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody ForeignLanguageRequest foreignLanguageRequest){
        foreignLanguageService.add(foreignLanguageRequest);
        return HttpStatus.CREATED;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        foreignLanguageService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody ForeignLanguageRequest foreignLanguageRequest) {
        foreignLanguageService.update(foreignLanguageRequest);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForeignLanguageResponse> getById(@PathVariable("id") Long id){
        ForeignLanguageResponse foreignLanguageResponse = foreignLanguageService.getById(id);
        return new ResponseEntity<>(foreignLanguageResponse,HttpStatus.OK);
    }
}
