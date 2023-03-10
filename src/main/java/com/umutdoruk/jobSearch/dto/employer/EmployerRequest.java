package com.umutdoruk.jobSearch.dto.employer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerRequest {

   /* private Long id;*/
    private String companyName;
    private String website;
    private String companyTelephoneNumber;
    private Long userId;
}
