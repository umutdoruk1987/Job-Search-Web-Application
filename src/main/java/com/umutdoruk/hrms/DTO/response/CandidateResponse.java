package com.umutdoruk.hrms.DTO.response;

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
    private LocalDate yearOfBirth;
    private Long resumeId;
    private Long userId;

    public static CandidateResponse of(Candidate candidate, Long resumeId, Long userId){
        return new CandidateResponse(candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getTelephoneNumber(),
                candidate.getYearOfBirth(),
                candidate.getResume().getId(),
                candidate.getUser().getId());
    }
}
