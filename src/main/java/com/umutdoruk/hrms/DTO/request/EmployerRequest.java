package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRequest {

    private String companyName;
    private String website;
    private String companyTelephoneNumber;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private Boolean isActive;
}
