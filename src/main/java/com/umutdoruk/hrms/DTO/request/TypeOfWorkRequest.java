package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfWorkRequest {

    private Long typeOfWorksId;
    private String name;
    private Long jobAdvertisementId;
}
