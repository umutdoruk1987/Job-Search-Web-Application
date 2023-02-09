package com.umutdoruk.jobSearch.service.services;

import com.umutdoruk.jobSearch.dto.request.WorkingTimeRequest;
import com.umutdoruk.jobSearch.dto.response.WorkingTimeResponse;
import com.umutdoruk.jobSearch.entities.WorkingTime;

import java.util.List;

public interface WorkingTimeService {
    WorkingTimeResponse create(WorkingTimeRequest workingTimeRequest);
    WorkingTimeResponse update (WorkingTimeRequest workingTimeRequest);
    void delete (Long id);
    WorkingTime getWorkingTimeById(Long workingTimeId);
    WorkingTime getWorkingTimeByJobAdvertisementId(Long id);
    WorkingTimeResponse getWorkingTimeResponseById(Long workingTimeId);
    List<WorkingTimeResponse> getWorkingTimeResponseByName(String workingTimeName);
    List<WorkingTimeResponse> getAllWorkingTimeResponses();
}
