package com.umutdoruk.jobSearch.dto.response;

import com.umutdoruk.jobSearch.entities.User;
import com.umutdoruk.jobSearch.enums.Role;
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
        return new UserSignInResponse(user.getUsername(), user.getRole().toString(), token);
    }
}
