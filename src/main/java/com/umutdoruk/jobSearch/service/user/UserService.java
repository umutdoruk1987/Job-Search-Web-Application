package com.umutdoruk.jobSearch.service.user;

import com.umutdoruk.jobSearch.dto.user.UserSignInRequest;
import com.umutdoruk.jobSearch.dto.user.UserSignupRequest;
import com.umutdoruk.jobSearch.dto.user.UserSignInResponse;
import com.umutdoruk.jobSearch.dto.user.UserSignupResponse;
import com.umutdoruk.jobSearch.entities.user.User;

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
