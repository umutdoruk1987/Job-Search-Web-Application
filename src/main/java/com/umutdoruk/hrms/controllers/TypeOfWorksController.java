package com.umutdoruk.hrms.controllers;

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
    public HttpStatus add (@RequestBody TypeOfWork typeOfWork){
        typeOfWorkService.add(typeOfWork);
        return HttpStatus.CREATED;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TypeOfWork>> getAll(){
        List<TypeOfWork> typeOfWorkList = typeOfWorkService.getAll();
        return new ResponseEntity<>(typeOfWorkList, HttpStatus.OK);
    }

    @GetMapping("/{id}}")
    public ResponseEntity<TypeOfWork> getById(@PathVariable ("id") Long id){
         TypeOfWork typeOfWork = typeOfWorkService.getById(id);
        return new ResponseEntity<>(typeOfWork, HttpStatus.OK);
    }

    @PostMapping("/update")
    public HttpStatus update(@RequestBody TypeOfWork typeOfWork){
        typeOfWorkService.update(typeOfWork);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id){
        typeOfWorkService.delete(id);
        return HttpStatus.OK;
    }

}
