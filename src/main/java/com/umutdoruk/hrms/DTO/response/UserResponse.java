package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String email;
    private String username;
    private LocalDate createdDate;
    private boolean active;

    public static UserResponse of(User user){
        return new UserResponse(user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getCreatedDate(),
                user.isActive());
    }
}
