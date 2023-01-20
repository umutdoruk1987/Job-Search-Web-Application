package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.service.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {

    private final EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employer>> getAll(){
         List<Employer> employerList = employerService.getAll();
        return new ResponseEntity<>(employerList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody Employer employer){
        employerService.add(employer);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getByEmail")
    public HttpStatus getByEmail(String email){
        Employer employer = employerService.findByEmail(email);
        return HttpStatus.OK;
    }
}
