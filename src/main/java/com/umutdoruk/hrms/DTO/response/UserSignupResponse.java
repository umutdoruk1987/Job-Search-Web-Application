package com.umutdoruk.hrms.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate createdDate;
    private boolean active;
    private String roleName;

    public static UserSignupResponse of(User user){
        return new UserSignupResponse(user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getCreatedDate(),
                user.isActive(),
                user.getRoleName());
    }
}
