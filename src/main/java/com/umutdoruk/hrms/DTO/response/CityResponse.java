package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {

    private String cityName;
    private Long cityId;

    public static CityResponse of (City city){
        if (city!=null)return new CityResponse(city.getCityName(), city.getId());
        else return new CityResponse();
    }

}
