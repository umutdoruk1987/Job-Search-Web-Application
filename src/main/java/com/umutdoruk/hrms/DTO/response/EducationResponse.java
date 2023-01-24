package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.Education;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponse {

    private String schoolName;
    private Long educationId;
    private Long resumeId;
    private LocalDate startDate;
    private LocalDate graduationDate;

    public static EducationResponse of (Education education){
        return new EducationResponse(
                education.getSchoolName(),
                education.getId(),
                education.getResume().getId(),
                education.getStartDate(),
                education.getGraduationDate());
    }
}

