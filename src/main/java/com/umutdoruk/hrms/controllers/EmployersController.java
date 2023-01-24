package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.EmployerRequest;
import com.umutdoruk.hrms.DTO.response.EmployerResponse;
import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.repository.EmployerRepository;
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
    private final EmployerRepository employerRepository;

    @Autowired
    public EmployersController(EmployerService employerService,
                               EmployerRepository employerRepository) {
        this.employerService = employerService;
        this.employerRepository = employerRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployerResponse>> getAll(){
         List<EmployerResponse> employerList = employerService.getAll();
        return new ResponseEntity<>(employerList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus add(@RequestBody EmployerRequest employerRequest){
        employerService.add(employerRequest);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<EmployerResponse> getByEmail(String email){
        EmployerResponse employerResponse = employerService.findByEmail(email);
        return new ResponseEntity<>(employerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        employerService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping("/update")
    public HttpStatus update(@RequestBody EmployerRequest employerRequest) {
        employerService.update(employerRequest);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerResponse> getById(@PathVariable("id") Long id){
        EmployerResponse employerResponse = employerService.findById(id);
        return new ResponseEntity<>(employerResponse,HttpStatus.OK);
    }


}
