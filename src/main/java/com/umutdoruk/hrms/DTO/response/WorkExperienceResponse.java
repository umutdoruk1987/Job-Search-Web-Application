package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.WorkExperience;
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
    private LocalDate startDate;
    private LocalDate endDate;
    private Long resumeId;

    public static WorkExperienceResponse of(WorkExperience workExperience){
        return new WorkExperienceResponse(workExperience.getId(),
                workExperience.getJobName(),
                workExperience.getJobPositionName(),
                workExperience.getStartDate(),
                workExperience.getEndDate(),
                workExperience.getResume().getId());
    }
}
