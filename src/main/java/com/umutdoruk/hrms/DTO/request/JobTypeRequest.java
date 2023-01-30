package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobTypeRequest {

    private Long typeOfWorksId;
    private String name;
    private Long jobAdvertisementId;
}
