package com.umutdoruk.jobSearch.dto.response;

import com.umutdoruk.jobSearch.entities.WorkingTime;
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

        if (workingTime !=null) return new WorkingTimeResponse(workingTime.getId(), workingTime.getName());
        else return new WorkingTimeResponse();
    }
}
