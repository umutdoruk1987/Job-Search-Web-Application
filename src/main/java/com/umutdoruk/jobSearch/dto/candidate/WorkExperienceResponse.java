package com.umutdoruk.jobSearch.dto.candidate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umutdoruk.jobSearch.entities.candidate.WorkExperience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceResponse {

    private Long workExperienceId;
    private String jobName;
    private String jobPositionName;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate endDate;
    private Long resumeId;

    public static WorkExperienceResponse of(WorkExperience workExperience){
        if (workExperience!=null) return new WorkExperienceResponse(workExperience.getId(),
                workExperience.getJobName(),
                workExperience.getJobPositionName(),
                workExperience.getStartDate(),
                workExperience.getEndDate(),
                workExperience.getResume().getId());
        else return new WorkExperienceResponse();
    }
}
