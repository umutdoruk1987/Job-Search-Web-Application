package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.UserSignInRequest;
import com.umutdoruk.jobSearch.dto.request.UserSignupRequest;
import com.umutdoruk.jobSearch.dto.response.UserSignInResponse;
import com.umutdoruk.jobSearch.dto.response.UserSignupResponse;
import com.umutdoruk.jobSearch.entities.User;

public interface UserService {

    UserSignupResponse create (UserSignupRequest userSignupRequest);
    UserSignInResponse login(UserSignInRequest userSignInRequest);
    void delete (/*Long id*/);
    UserSignupResponse update (UserSignupRequest userSignupRequest);
    User getUserById(Long id);
    User getUserByUsername(String username);
    /*UserSignupResponse getUserResponseById(Long id);
    UserSignupResponse getUserResponseByEmail(String email);*/
}
