package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.EmployerRequest;
import com.umutdoruk.hrms.DTO.response.EmployerResponse;
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
    // ROLE_EMPLOYER
    @PostMapping("/create")
    public HttpStatus create(@RequestBody EmployerRequest employerRequest){
        employerService.create(employerRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/update")
    public HttpStatus update(@RequestBody EmployerRequest employerRequest) {
        employerService.update(employerRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    /*@DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        employerService.delete(id);
        return HttpStatus.OK;
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<EmployerResponse> getEmployerResponseById (@PathVariable("id") Long id){
        EmployerResponse employerResponse = employerService.getEmployerResponseById(id);
        return new ResponseEntity<>(employerResponse,HttpStatus.OK);
    }

    /*@GetMapping("/getByEmail")
    public ResponseEntity<EmployerResponse> getEmployerResponseByEmail (String email){
        EmployerResponse employerResponse = employerService.getEmployerResponseByEmail(email);
        return new ResponseEntity<>(employerResponse, HttpStatus.OK);
    }*/

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployerResponse>> getAllEmployerResponses (){
        List<EmployerResponse> employerList = employerService.getAllEmployerResponses();
        return new ResponseEntity<>(employerList, HttpStatus.OK);
    }


}
