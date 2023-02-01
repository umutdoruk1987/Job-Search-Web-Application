package com.umutdoruk.hrms.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umutdoruk.hrms.entities.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate yearOfBirth;
    private ResumeResponse resumeResponse;
    private Long userId;

    public static CandidateResponse of(Candidate candidate, ResumeResponse resumeResponse){
        return new CandidateResponse(candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getTelephoneNumber(),
                candidate.getYearOfBirth(),
                resumeResponse,
                candidate.getUser().getId());
    }
}
