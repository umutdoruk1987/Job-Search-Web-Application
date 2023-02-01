package com.umutdoruk.hrms.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umutdoruk.hrms.entities.JobAdvertisement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementResponse {

    private Long jobAdvertisementId;
    private String description;
    private Long minSalary;
    private Long maxSalary;
    private int numberOfOpenJobPosition;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate applicationDeadline;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate createdDate;
    private boolean active;
    private CityResponse cityResponse;
    private JobPositionResponse jobPositionResponse;
    private JobTypeResponse jobTypeResponse;
    private TypeOfWorkResponse typeOfWorkResponse;
    private Long employerId;

    public static JobAdvertisementResponse of(JobAdvertisement jobAdvertisement){
        JobAdvertisementResponse jobAdvertisementResponse =  new JobAdvertisementResponse();
        jobAdvertisementResponse.setJobAdvertisementId(jobAdvertisement.getId());
        jobAdvertisementResponse.setDescription(jobAdvertisement.getDescription());
        jobAdvertisementResponse.setMinSalary(jobAdvertisement.getMinSalary());
        jobAdvertisementResponse.setMaxSalary(jobAdvertisement.getMaxSalary());
        jobAdvertisementResponse.setNumberOfOpenJobPosition(jobAdvertisement.getNumberOfOpenJobPosition());
        jobAdvertisementResponse.setApplicationDeadline(jobAdvertisement.getApplicationDeadline());
        jobAdvertisementResponse.setCreatedDate(jobAdvertisement.getCreatedDate());
        jobAdvertisementResponse.setActive(jobAdvertisement.isActive());
        jobAdvertisementResponse.setEmployerId(jobAdvertisement.getEmployer().getId());
        if (jobAdvertisement.getCity()!=null) CityResponse.of(jobAdvertisement.getCity());
        if (jobAdvertisement.getJobPosition()!=null) JobPositionResponse.of(jobAdvertisement.getJobPosition());
        if (jobAdvertisement.getJobType()!=null)JobTypeResponse.of(jobAdvertisement.getJobType());
        if (jobAdvertisement.getTypeOfWork()!=null)TypeOfWorkResponse.of(jobAdvertisement.getTypeOfWork());
        return jobAdvertisementResponse;
    }

}
