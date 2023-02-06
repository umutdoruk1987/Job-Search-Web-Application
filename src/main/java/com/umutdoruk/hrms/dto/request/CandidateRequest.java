package com.umutdoruk.hrms.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate yearOfBirth;
    private Long userId;

}
