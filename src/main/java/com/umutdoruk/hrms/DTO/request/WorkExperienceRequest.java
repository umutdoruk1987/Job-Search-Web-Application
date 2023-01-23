package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceRequest {

    private String jobName;
    private String jobPositionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long resumeId;
}
