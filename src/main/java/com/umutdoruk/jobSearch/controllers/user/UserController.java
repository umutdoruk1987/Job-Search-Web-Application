package com.umutdoruk.jobSearch.controllers.user;

import com.umutdoruk.jobSearch.dto.user.UserSignInRequest;
import com.umutdoruk.jobSearch.dto.user.UserSignupRequest;
import com.umutdoruk.jobSearch.dto.user.UserSignInResponse;
import com.umutdoruk.jobSearch.dto.user.UserSignupResponse;
import com.umutdoruk.jobSearch.entities.user.User;
import com.umutdoruk.jobSearch.exception.UnauthorizedException;
import com.umutdoruk.jobSearch.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup") // NO ROLE
    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest userSignupRequest){
        UserSignupResponse userSignupResponse = userService.create(userSignupRequest);
        return new ResponseEntity<>(userSignupResponse,HttpStatus.CREATED);
    }

    @PostMapping("signIn")  // NO ROLE
    public UserSignInResponse signIn(@RequestBody UserSignInRequest userSignInRequest){

        User user = userService.getUserByUsername(userSignInRequest.getUsername());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), userSignInRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw  new UnauthorizedException(e.getMessage());
        }

        UserSignInResponse signInResponse = userService.login(userSignInRequest);
        return userService.login(userSignInRequest);
    }

    @PutMapping("update") // ROLE_CANDIDATE, ROLE_EMPLOYER
    public ResponseEntity<UserSignupResponse> update (@RequestBody UserSignupRequest userSignupRequest) {
        UserSignupResponse userSignupResponse = userService.update(userSignupRequest);
        return new ResponseEntity<>(userSignupResponse,HttpStatus.OK);
    }

    @DeleteMapping("delete")  // ROLE_CANDIDATE, ROLE_EMPLOYER
    public HttpStatus delete (/*@PathVariable ("id") Long id*/){
        userService.delete(/*id*/);
        return HttpStatus.OK;
    }
    /*
    @GetMapping("/id/{id}")  // ROLE_CANDIDATE, ROLE_EMPLOYER
    public ResponseEntity<UserSignupResponse> getUserResponseById(@PathVariable("id") Long id){
        UserSignupResponse userSignupResponse = userService.getUserResponseById(id);
        return new ResponseEntity<>(userSignupResponse, HttpStatus.OK);
    }

    @GetMapping("/email/{email}") // ROLE_CANDIDATE, ROLE_EMPLOYER
    public ResponseEntity<UserSignupResponse> getUserResponseByEmail(@PathVariable("email") String email){
        UserSignupResponse userSignupResponse = userService.getUserResponseByEmail(email);
        return new ResponseEntity<>(userSignupResponse, HttpStatus.OK);
    }
*/
}
