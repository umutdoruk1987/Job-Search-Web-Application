package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.Role;
import com.umutdoruk.hrms.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupResponse {

    private Long userId;
    private String email;
    private String username;
    private LocalDate createdDate;
    private boolean active;
    private Role role;

    public static UserSignupResponse of(User user){
        return new UserSignupResponse(user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getCreatedDate(),
                user.isActive(),
                user.getRole());
    }
}
