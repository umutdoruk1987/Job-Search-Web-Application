package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerResponse {

    private Long id;
    private String companyName;
    private String website;
    private String companyTelephoneNumber;
    private List<JobAdvertisementResponse> jobAdvertisementList;

    public static EmployerResponse of(Employer employer, List<JobAdvertisementResponse> jobAdvertisementList){
        return new EmployerResponse(employer.getId(),
                employer.getCompanyName(),
                employer.getWebsite(),
                employer.getCompanyTelephoneNumber(),
                jobAdvertisementList);
    }
}
