package com.umutdoruk.hrms.controllers;

import com.umutdoruk.hrms.DTO.request.UserSignupRequest;
import com.umutdoruk.hrms.DTO.response.UserSignupResponse;
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
    public HttpStatus create(@RequestBody UserSignupRequest userSignupRequest){
        userService.create(userSignupRequest);
        return HttpStatus.CREATED;
    }

    @PutMapping("/update")
    public HttpStatus update (@RequestBody UserSignupRequest userSignupRequest, @RequestParam Long userId) {
        userService.update(userSignupRequest,userId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete (@PathVariable ("id") Long id){
        userService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSignupResponse> getUserResponseById(@PathVariable("id") Long id){
        UserSignupResponse userSignupResponse = userService.getUserResponseById(id);
        return new ResponseEntity<>(userSignupResponse, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserSignupResponse> getUserResponseByEmail(@PathVariable("email") String email){
        UserSignupResponse userSignupResponse = userService.getUserResponseByEmail(email);
        return new ResponseEntity<>(userSignupResponse, HttpStatus.OK);
    }

}
