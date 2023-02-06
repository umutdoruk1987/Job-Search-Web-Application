package com.umutdoruk.hrms.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
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

