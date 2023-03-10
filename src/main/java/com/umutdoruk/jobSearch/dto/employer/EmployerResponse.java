package com.umutdoruk.jobSearch.dto.employer;

import com.umutdoruk.jobSearch.entities.employer.Employer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerResponse {

    /*private Long employerId;*/
    private String companyName;
    private String website;
    private String companyTelephoneNumber;
    private List<JobAdvertisementResponse> jobAdvertisementList;

    public static EmployerResponse of(Employer employer,
                                      List<JobAdvertisementResponse> jobAdvertisementList
                                      ){
        return new EmployerResponse(/*employer.getId(),*/
                employer.getCompanyName(),
                employer.getWebsite(),
                employer.getCompanyTelephoneNumber(),
                jobAdvertisementList);
    }
}
