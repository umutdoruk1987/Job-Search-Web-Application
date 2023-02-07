package com.umutdoruk.jobSearch.dto.response;

import com.umutdoruk.jobSearch.entities.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobTitleResponse {

    private Long jobTitleId;
    private String name;

    public static JobTitleResponse of(JobTitle jobTitle){

        if(jobTitle !=null)return new JobTitleResponse(jobTitle.getId(), jobTitle.getName());
        else return new JobTitleResponse();
    }
}
