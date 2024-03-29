package com.umutdoruk.jobSearch.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequest {

    private Long userId;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private String role;
    private Boolean active;

}
