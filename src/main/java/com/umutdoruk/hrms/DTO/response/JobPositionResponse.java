package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPositionResponse {

    private Long jobPositionId;
    private String name;

    public static JobPositionResponse of(JobPosition jobPosition){

        if (jobPosition!=null) return new JobPositionResponse(jobPosition.getId(),jobPosition.getName());
        else return new JobPositionResponse();
    }
}
