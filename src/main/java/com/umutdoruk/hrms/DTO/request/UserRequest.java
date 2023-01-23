package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private boolean isActive;

}
