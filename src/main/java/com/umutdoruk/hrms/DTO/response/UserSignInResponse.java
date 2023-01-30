package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInResponse {

    private String username;
    private String role;
    private String token;

    public static UserSignInResponse of (User user, String token){
        return new UserSignInResponse(user.getUsername(), user.getRoleName(), token);
    }
}
