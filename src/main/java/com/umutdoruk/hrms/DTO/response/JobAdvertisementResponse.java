package com.umutdoruk.hrms.DTO.response;

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
    private LocalDate applicationDeadline;
    private LocalDate createdDate;
    private boolean active;
    private Long cityId;
    private Long jobPositionId;
    private Long jobTypeId;
    private Long typeOfWorkId;
    private Long employerId;

    public static JobAdvertisementResponse of(JobAdvertisement jobAdvertisement){
        return new JobAdvertisementResponse(jobAdvertisement.getId(),
                jobAdvertisement.getDescription(),
                jobAdvertisement.getMinSalary(),
                jobAdvertisement.getMaxSalary(),
                jobAdvertisement.getNumberOfOpenJobPosition(),
                jobAdvertisement.getApplicationDeadline(),
                jobAdvertisement.getCreatedDate(),
                jobAdvertisement.isActive(),
                jobAdvertisement.getCity().getId(),
                jobAdvertisement.getJobPosition().getId(),
                jobAdvertisement.getJobType().getId(),
                jobAdvertisement.getTypeOfWork().getId(),
                jobAdvertisement.getEmployer().getId());
    }

}
