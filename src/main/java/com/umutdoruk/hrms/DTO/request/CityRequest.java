package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {

    private String cityName;
    private Long jobAdvertisementId;
}
