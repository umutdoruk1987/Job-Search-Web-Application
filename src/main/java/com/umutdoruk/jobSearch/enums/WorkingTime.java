package com.umutdoruk.jobSearch.enums;

import com.umutdoruk.jobSearch.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WorkingTime {
    FULL_TIME,
    PART_TIME,
    MINI_JOB,
    FLEXIBLE;

    public static WorkingTime findByName(String workingTime){
        return Arrays.stream(WorkingTime.values())
                .filter(workingTime1 -> workingTime1.name().equals(workingTime))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Working Time is not found. Available times are : "
                        + Arrays.toString(EmploymentType.values())));
    }

}
