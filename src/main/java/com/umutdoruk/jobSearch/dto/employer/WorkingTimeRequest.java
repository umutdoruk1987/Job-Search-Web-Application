package com.umutdoruk.jobSearch.dto.employer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingTimeRequest {

    private Long workingTimeId;
    private String name;
    private Long jobAdvertisementId;
}
