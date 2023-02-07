package com.umutdoruk.jobSearch.controllers;

import com.umutdoruk.jobSearch.dto.request.EmploymentTypeRequest;
import com.umutdoruk.jobSearch.dto.response.EmploymentTypeResponse;
import com.umutdoruk.jobSearch.service.services.EmploymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employment_type")
public class EmploymentTypeController {

    private final EmploymentTypeService employmentTypeService;

    @Autowired
    public EmploymentTypeController(EmploymentTypeService employmentTypeService) {
        this.employmentTypeService = employmentTypeService;
    }
    // ROLE_EMPLOYER
    @PostMapping("/create")
    public HttpStatus create (@RequestBody EmploymentTypeRequest employmentTypeRequest){
        employmentTypeService.create(employmentTypeRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/update")
    public HttpStatus update(@RequestBody EmploymentTypeRequest employmentTypeRequest){
        employmentTypeService.update(employmentTypeRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        employmentTypeService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}}")
    public ResponseEntity<EmploymentTypeResponse> getEmploymentTypeResponseById (@PathVariable ("id") Long id){
         EmploymentTypeResponse employmentTypeResponse = employmentTypeService.getEmploymentTypeResponseById(id);
        return new ResponseEntity<>(employmentTypeResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmploymentTypeResponse>> getAllEmploymentTypeResponses(){
        List<EmploymentTypeResponse> employmentTypeResponseList = employmentTypeService.getAllEmploymentTypeResponses();
        return new ResponseEntity<>(employmentTypeResponseList, HttpStatus.OK);
    }
}
