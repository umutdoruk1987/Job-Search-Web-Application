package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.TypeOfWorkRequest;
import com.umutdoruk.hrms.DTO.response.TypeOfWorkResponse;
import com.umutdoruk.hrms.service.services.TypeOfWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type_Of_Works")
public class TypeOfWorksController {

    private final TypeOfWorkService typeOfWorkService;

    @Autowired
    public TypeOfWorksController(TypeOfWorkService typeOfWorkService) {
        this.typeOfWorkService = typeOfWorkService;
    }
    // ROLE_EMPLOYER
    @PostMapping("/create")
    public HttpStatus create (@RequestBody TypeOfWorkRequest typeOfWorkRequest){
        typeOfWorkService.create(typeOfWorkRequest);
        return HttpStatus.CREATED;
    }
    // ROLE_EMPLOYER
    @PutMapping("/update")
    public HttpStatus update(@RequestBody TypeOfWorkRequest typeOfWorkRequest){
        typeOfWorkService.update(typeOfWorkRequest);
        return HttpStatus.OK;
    }
    // ROLE_EMPLOYER
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        typeOfWorkService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}}")
    public ResponseEntity<TypeOfWorkResponse> getTypeOfWorkResponseById (@PathVariable ("id") Long id){
         TypeOfWorkResponse typeOfWorkResponse = typeOfWorkService.getTypeOfWorkResponseById(id);
        return new ResponseEntity<>(typeOfWorkResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TypeOfWorkResponse>> getAllTypeResponsesOfWork(){
        List<TypeOfWorkResponse> typeOfWorkResponseList = typeOfWorkService.getAllTypeOfWorkResponses();
        return new ResponseEntity<>(typeOfWorkResponseList, HttpStatus.OK);
    }
}
