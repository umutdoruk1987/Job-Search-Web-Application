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
    private Long jobAdvertisementId;

    public static JobPositionResponse of(JobPosition jobPosition){
        return new JobPositionResponse(jobPosition.getId(),
                jobPosition.getName(),
                jobPosition.getJobAdvertisement().getId());
    }
}
