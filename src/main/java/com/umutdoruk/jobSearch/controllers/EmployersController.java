package com.umutdoruk.jobSearch.controllers;

import com.umutdoruk.jobSearch.dto.request.EmployerRequest;
import com.umutdoruk.jobSearch.dto.response.EmployerResponse;
import com.umutdoruk.jobSearch.service.services.EmployerService;
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

    /*@PostMapping("/create")  // ROLE_EMPLOYER
    public HttpStatus create(@RequestBody EmployerRequest employerRequest){
        employerService.create(employerRequest);
        return HttpStatus.CREATED;
    }*/

    @PutMapping("/update")  // ROLE_EMPLOYER
    public ResponseEntity<EmployerResponse> update(@RequestBody EmployerRequest employerRequest) {
        EmployerResponse employerResponse = employerService.update(employerRequest);
        return new ResponseEntity<>(employerResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}") // ROLE_CANDIDATE, ROLE_EMPLOYER
    public ResponseEntity<EmployerResponse> getEmployerResponseById (@PathVariable("id") Long id){
        EmployerResponse employerResponse = employerService.getEmployerResponseById(id);
        return new ResponseEntity<>(employerResponse,HttpStatus.OK);
    }

    @GetMapping("/getAll") //ROLE_CANDIDATE , ROLE_EMPLOYER
    public ResponseEntity<List<EmployerResponse>> getAllEmployerResponses (){
        List<EmployerResponse> employerList = employerService.getAllEmployerResponses();
        return new ResponseEntity<>(employerList, HttpStatus.OK);
    }

}
