package com.umutdoruk.hrms.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate applicationDeadline;
    private Boolean active;
    private Long employerId;
    /*private Long cityId;
    private Long jobPositionId;
    private Long jobTypeId;
    private Long typeOfWorkId;*/

}
