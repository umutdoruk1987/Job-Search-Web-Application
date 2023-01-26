package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.UserRequest;
import com.umutdoruk.hrms.DTO.response.UserResponse;
import com.umutdoruk.hrms.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public HttpStatus create(@RequestBody UserRequest userRequest){
        userService.create(userRequest);
        return HttpStatus.CREATED;
    }

    @PutMapping("/update")
    public HttpStatus update (@RequestBody UserRequest userRequest, @RequestParam Long userId) {
        userService.update(userRequest,userId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete (@PathVariable ("id") Long id){
        userService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserResponseById(@PathVariable("id") Long id){
        UserResponse userResponse = userService.getUserResponseById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUserResponseByEmail(@PathVariable("email") String email){
        UserResponse userResponse = userService.getUserResponseByEmail(email);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

}
