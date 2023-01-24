package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.TypeOfWorkRequest;
import com.umutdoruk.hrms.DTO.response.TypeOfWorkResponse;
import com.umutdoruk.hrms.entities.TypeOfWork;
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

    @PostMapping("/add")
    public HttpStatus add (@RequestBody TypeOfWorkRequest typeOfWorkRequest){
        typeOfWorkService.add(typeOfWorkRequest);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TypeOfWorkResponse>> getAll(){
        List<TypeOfWorkResponse> typeOfWorkResponseList = typeOfWorkService.getAll();
        return new ResponseEntity<>(typeOfWorkResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}}")
    public ResponseEntity<TypeOfWorkResponse> getById(@PathVariable ("id") Long id){
         TypeOfWorkResponse typeOfWorkResponse = typeOfWorkService.getById(id);
        return new ResponseEntity<>(typeOfWorkResponse, HttpStatus.OK);
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody TypeOfWorkRequest typeOfWorkRequest){
        typeOfWorkService.update(typeOfWorkRequest);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        typeOfWorkService.delete(id);
        return HttpStatus.OK;
    }

}
