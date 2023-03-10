package com.umutdoruk.jobSearch.controllers.employer;

import com.umutdoruk.jobSearch.dto.employer.EmploymentTypeRequest;
import com.umutdoruk.jobSearch.dto.employer.EmploymentTypeResponse;
import com.umutdoruk.jobSearch.service.employer.EmploymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employment_type/")
public class EmploymentTypeController {

    private final EmploymentTypeService employmentTypeService;

    @Autowired
    public EmploymentTypeController(EmploymentTypeService employmentTypeService) {
        this.employmentTypeService = employmentTypeService;
    }

    @PostMapping() // ROLE_EMPLOYER
    public ResponseEntity<EmploymentTypeResponse> create (@RequestBody EmploymentTypeRequest employmentTypeRequest){
        EmploymentTypeResponse employmentTypeResponse = employmentTypeService.create(employmentTypeRequest);
        return new ResponseEntity<>(employmentTypeResponse,HttpStatus.CREATED);
    }

    @PutMapping() // ROLE_EMPLOYER
    public ResponseEntity<EmploymentTypeResponse> update(@RequestBody EmploymentTypeRequest employmentTypeRequest){
        EmploymentTypeResponse employmentTypeResponse = employmentTypeService.update(employmentTypeRequest);
        return new ResponseEntity<>(employmentTypeResponse,HttpStatus.OK);
    }

    @DeleteMapping("{id}") // ROLE_EMPLOYER
    public HttpStatus delete(@PathVariable("id") Long id){
        employmentTypeService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("{id}}") // ROLE_EMPLOYER
    public ResponseEntity<EmploymentTypeResponse> getEmploymentTypeResponseById (@PathVariable ("id") Long id){
         EmploymentTypeResponse employmentTypeResponse = employmentTypeService.getEmploymentTypeResponseById(id);
        return new ResponseEntity<>(employmentTypeResponse, HttpStatus.OK);
    }

    @GetMapping("getAll") // ROLE_EMPLOYER
    public ResponseEntity<List<EmploymentTypeResponse>> getAllEmploymentTypeResponses(){
        List<EmploymentTypeResponse> employmentTypeResponseList = employmentTypeService.getAllEmploymentTypeResponses();
        return new ResponseEntity<>(employmentTypeResponseList, HttpStatus.OK);
    }
}
