package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.UserSignupRequest;
import com.umutdoruk.hrms.DTO.response.UserSignupResponse;
import com.umutdoruk.hrms.entities.User;

public interface UserService {

    void create (UserSignupRequest userSignupRequest);
    void delete (Long id);
    void update (UserSignupRequest userSignupRequest);
    User getUserById(Long id);
    UserSignupResponse getUserResponseById(Long id);
    UserSignupResponse getUserResponseByEmail(String email);
}
