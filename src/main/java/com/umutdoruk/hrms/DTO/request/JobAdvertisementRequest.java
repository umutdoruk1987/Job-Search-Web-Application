package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementRequest {

    private Long jobAdvertisementId;
    private String description;
    private Long minSalary;
    private Long maxSalary;
    private Integer numberOfOpenJobPosition;
    private LocalDate applicationDeadline;
    private Boolean active;
    private Long cityId;
    private Long jobPositionId;
    private Long jobTypeId;
    private Long typeOfWorkId;
    private Long employerId;
}
