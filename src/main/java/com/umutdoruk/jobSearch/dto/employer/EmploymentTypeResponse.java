package com.umutdoruk.jobSearch.dto.employer;


import com.umutdoruk.jobSearch.entities.employer.EmploymentType;
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

        if (employmentType !=null)
            return new EmploymentTypeResponse(employmentType.getId(), employmentType.getTypeName().name());

        return new EmploymentTypeResponse();
    }
}
