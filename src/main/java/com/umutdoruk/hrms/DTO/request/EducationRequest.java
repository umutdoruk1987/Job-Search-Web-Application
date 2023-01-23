package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequest {

    private String schoolName;
    private Long resumeId;
    private LocalDate startDate;
    private LocalDate graduationDate;


}

