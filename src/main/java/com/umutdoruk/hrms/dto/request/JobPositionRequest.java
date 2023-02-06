package com.umutdoruk.hrms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionRequest {

    private Long jobPositionId;
    private String name;
    private Long jobAdvertisementId;
}
