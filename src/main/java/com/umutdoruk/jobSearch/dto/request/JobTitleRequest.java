package com.umutdoruk.jobSearch.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobTitleRequest {

    private Long jobTitleId;
    private String name;
    private Long jobAdvertisementId;
}
