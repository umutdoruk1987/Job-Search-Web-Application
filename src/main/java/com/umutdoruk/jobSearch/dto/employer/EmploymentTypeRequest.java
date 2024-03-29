package com.umutdoruk.jobSearch.dto.employer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentTypeRequest {

    private Long employmentTypeId;
    private String name;
    private Long jobAdvertisementId;
}
