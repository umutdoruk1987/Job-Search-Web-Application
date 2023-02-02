package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobTypeResponse {

    private Long jobTypeId;
    private String name;

    public static JobTypeResponse of(JobType jobType){

        if(jobType!=null)return new JobTypeResponse(jobType.getId(),jobType.getName());
        else return new JobTypeResponse();
    }
}
