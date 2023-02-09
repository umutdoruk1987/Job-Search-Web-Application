package com.umutdoruk.jobSearch.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umutdoruk.jobSearch.entities.User;
import com.umutdoruk.jobSearch.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupResponse {

    /*private Long userId;*/
    private String email;
    private String username;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate createdDate;
    private Boolean active;
    private String role;

    public static UserSignupResponse of(User user){
        return new UserSignupResponse(/*user.getId(),*/
                user.getEmail(),
                user.getUsername(),
                user.getCreatedDate(),
                user.getActive(),
                user.getRole().toString());
    }
}
