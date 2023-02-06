package com.umutdoruk.hrms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {

    private Long cityId;
    private String cityName;
    private Long jobAdvertisementId;
}
