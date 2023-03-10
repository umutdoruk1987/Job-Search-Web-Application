package com.umutdoruk.jobSearch.dto.employer;

import com.umutdoruk.jobSearch.entities.employer.WorkingTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingTimeResponse {

    private Long workingTimeId;
    private String name;

    public static WorkingTimeResponse of(WorkingTime workingTime){

        if (workingTime !=null) return new WorkingTimeResponse(workingTime.getId(), workingTime.getName().name());
        else return new WorkingTimeResponse();
    }
}
