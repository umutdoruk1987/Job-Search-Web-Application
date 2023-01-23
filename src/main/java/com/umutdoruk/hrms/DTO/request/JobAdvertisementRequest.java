package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementRequest {

    private String description;
    private Long minSalary;
    private Long maxSalary;
    private int numberOfOpenJobPosition;
    private LocalDate applicationDeadline;
    private boolean active;
    private Long cityId;
    private Long jobPositionId;
    private Long jobTypeId;
    private Long typeOfWorkId;
    private Long employerId;
}
