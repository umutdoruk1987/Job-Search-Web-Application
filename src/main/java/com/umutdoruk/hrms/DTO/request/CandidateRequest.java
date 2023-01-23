package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {

    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private LocalDate yearOfBirth;
    private Long resumeId;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private Boolean isActive;

}
