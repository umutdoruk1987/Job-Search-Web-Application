package com.umutdoruk.jobSearch.dto.employer;

import com.umutdoruk.jobSearch.entities.employer.JobLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobLocationResponse {

    private String jobLocationName;
    private Long jobLocationId;

    public static JobLocationResponse of (JobLocation jobLocation){
        if (jobLocation !=null)return new JobLocationResponse(jobLocation.getJobLocationName(), jobLocation.getId());
        else return new JobLocationResponse();
    }

}
