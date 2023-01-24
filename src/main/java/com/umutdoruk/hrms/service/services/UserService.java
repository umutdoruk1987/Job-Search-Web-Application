package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.UserRequest;
import com.umutdoruk.hrms.DTO.response.UserResponse;

public interface UserService {
    void register(UserRequest userRequest);
    UserResponse findByEmail(String email);
    Boolean isEmailExist(String email);
}
