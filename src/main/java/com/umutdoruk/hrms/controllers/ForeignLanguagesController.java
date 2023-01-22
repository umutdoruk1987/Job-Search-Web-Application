package com.umutdoruk.hrms.controllers;

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
    public ResponseEntity<List<ForeignLanguage>> getAll(){
        List<ForeignLanguage> foreignLanguageList = foreignLanguageService.getAll();
        return new ResponseEntity<>(foreignLanguageList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody ForeignLanguage foreignLanguage){
        foreignLanguageService.add(foreignLanguage);
        return HttpStatus.CREATED;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        foreignLanguageService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody ForeignLanguage foreignLanguage) {
        foreignLanguageService.update(foreignLanguage);
        return HttpStatus.OK;
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<ForeignLanguage> getById(@PathVariable Long id){
        ForeignLanguage foreignLanguage = foreignLanguageService.getById(id);
        return new ResponseEntity<>(foreignLanguage,HttpStatus.OK);
    }
}
