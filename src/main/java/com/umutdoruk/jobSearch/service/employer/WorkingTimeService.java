package com.umutdoruk.jobSearch.service.employer;

import com.umutdoruk.jobSearch.dto.employer.WorkingTimeRequest;
import com.umutdoruk.jobSearch.dto.employer.WorkingTimeResponse;
import com.umutdoruk.jobSearch.entities.employer.WorkingTime;

import java.util.List;

public interface WorkingTimeService {
    WorkingTimeResponse create(WorkingTimeRequest workingTimeRequest);
    WorkingTimeResponse update (WorkingTimeRequest workingTimeRequest);
    void delete (Long id);
    WorkingTime getWorkingTimeById(Long workingTimeId);
    WorkingTime getWorkingTimeByJobAdvertisementId(Long id);
    WorkingTimeResponse getWorkingTimeResponseById(Long workingTimeId);
    /*List<WorkingTimeResponse> getWorkingTimeResponseByName(String workingTimeName);*/
    List<WorkingTimeResponse> getAllWorkingTimeResponses();
}
