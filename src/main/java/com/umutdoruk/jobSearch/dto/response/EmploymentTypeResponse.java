package com.umutdoruk.jobSearch.dto.response;


import com.umutdoruk.jobSearch.entities.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentTypeResponse {

    private Long employmentTypeId;
    private String employmentTypeName;

    public static EmploymentTypeResponse of(EmploymentType employmentType){

        if (employmentType !=null)return new EmploymentTypeResponse(employmentType.getId(), employmentType.getName());
        else return new EmploymentTypeResponse();
    }
}
