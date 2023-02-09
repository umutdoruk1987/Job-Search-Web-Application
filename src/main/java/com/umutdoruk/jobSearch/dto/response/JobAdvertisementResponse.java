package com.umutdoruk.jobSearch.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umutdoruk.jobSearch.entities.JobAdvertisement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementResponse {

    private Long jobAdvertisementId;
    private String description;
    private Long minSalary;
    private Long maxSalary;
    private int numberOfOpenJobPosition;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate applicationDeadline;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate createdDate;
    private boolean active;
    private JobLocationResponse jobLocationResponse;
    private WorkingTimeResponse workingTimeResponse;
    private JobTitleResponse jobTitleResponse;
    private EmploymentTypeResponse employmentTypeResponse;
    /*private Long employerId;*/

    public static JobAdvertisementResponse of(JobAdvertisement jobAdvertisement,
                                              JobLocationResponse jobLocationResponse,
                                              WorkingTimeResponse workingTimeResponse,
                                              JobTitleResponse jobTitleResponse,
                                              EmploymentTypeResponse employmentTypeResponse){
        JobAdvertisementResponse jobAdvertisementResponse =  new JobAdvertisementResponse();
        jobAdvertisementResponse.setJobAdvertisementId(jobAdvertisement.getId());
        jobAdvertisementResponse.setDescription(jobAdvertisement.getDescription());
        jobAdvertisementResponse.setMinSalary(jobAdvertisement.getMinSalary());
        jobAdvertisementResponse.setMaxSalary(jobAdvertisement.getMaxSalary());
        jobAdvertisementResponse.setNumberOfOpenJobPosition(jobAdvertisement.getNumberOfOpenJobPosition());
        jobAdvertisementResponse.setApplicationDeadline(jobAdvertisement.getApplicationDeadline());
        jobAdvertisementResponse.setCreatedDate(jobAdvertisement.getCreatedDate());
        jobAdvertisementResponse.setActive(jobAdvertisement.isActive());
        /*jobAdvertisementResponse.setEmployerId(jobAdvertisement.getEmployer().getId());*/

        /*if (jobAdvertisement.getCity()!=null)
            jobAdvertisementResponse.setCityResponse(CityResponse.of(jobAdvertisement.getCity()));
        if (jobAdvertisement.getJobPosition()!=null)
            jobAdvertisementResponse.setJobPositionResponse(JobPositionResponse.of(jobAdvertisement.getJobPosition()));
        if (jobAdvertisement.getJobType()!=null)
            jobAdvertisementResponse.setJobTypeResponse(JobTypeResponse.of(jobAdvertisement.getJobType()));
        if (jobAdvertisement.getTypeOfWork()!=null)
            jobAdvertisementResponse.setTypeOfWorkResponse(TypeOfWorkResponse.of(jobAdvertisement.getTypeOfWork()));*/

        jobAdvertisementResponse.setJobLocationResponse(jobLocationResponse);
        jobAdvertisementResponse.setWorkingTimeResponse(workingTimeResponse);
        jobAdvertisementResponse.setJobTitleResponse(jobTitleResponse);
        jobAdvertisementResponse.setEmploymentTypeResponse(employmentTypeResponse);

        return jobAdvertisementResponse;
    }

}
