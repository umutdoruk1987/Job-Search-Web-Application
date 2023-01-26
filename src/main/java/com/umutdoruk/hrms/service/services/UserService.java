package com.umutdoruk.hrms.service.services;

import com.umutdoruk.hrms.DTO.request.UserRequest;
import com.umutdoruk.hrms.DTO.response.UserResponse;
import com.umutdoruk.hrms.entities.User;

public interface UserService {

    void create (UserRequest userRequest);
    void delete (Long id);
    void update (UserRequest userRequest, Long userId);
    User getUserById(Long id);
    UserResponse getUserResponseById(Long id);
    UserResponse getUserResponseByEmail(String email);
    Boolean isEmailExist(String email);
    Boolean isUsernameExist(String username);
    String createUsernameIfNoPresent(UserRequest userRequest);
}
