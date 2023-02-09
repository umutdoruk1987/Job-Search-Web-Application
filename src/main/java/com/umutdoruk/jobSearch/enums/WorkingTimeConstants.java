package com.umutdoruk.jobSearch.enums;

import com.umutdoruk.jobSearch.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WorkingTimeConstants {
    FULL_TIME,
    PART_TIME,
    MINI_JOB;

    public static WorkingTimeConstants findByName(String workingTime){
        return Arrays.stream(WorkingTimeConstants.values())
                .filter(workingTimeConstants1 -> workingTimeConstants1.name().equals(workingTime))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Working Time is not found. Available times are : "
                        + Arrays.toString(WorkingTimeConstants.values())));
    }

}
