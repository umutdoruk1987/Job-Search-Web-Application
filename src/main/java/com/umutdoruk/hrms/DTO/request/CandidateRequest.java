package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {

    private Long candidateId;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private LocalDate yearOfBirth;
    private Long userId;

}
