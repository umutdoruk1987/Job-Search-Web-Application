package com.umutdoruk.jobSearch.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobLocationRequest {

    private Long jobLocationId;
    private String jobLocationName;
    private Long jobAdvertisementId;
}
