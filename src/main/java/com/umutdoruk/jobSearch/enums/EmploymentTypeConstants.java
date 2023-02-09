package com.umutdoruk.jobSearch.enums;

import com.umutdoruk.jobSearch.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EmploymentTypeConstants {
    PERMANENT,
    TEMPORARY,
    SEASONAL,
    TRAINEE,
    VOCATIONAL_TRAINING,
    STUDENT_WORK,
    INTERNSHIP,
    PROJECT_WORK,
    CONTRACTOR;

    public static EmploymentTypeConstants findByName(String employmentType){
        return Arrays.stream(EmploymentTypeConstants.values())
                .filter(employmentTypeConstants1 -> employmentTypeConstants1.name().equals(employmentType))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Employment Type is not found. Available types are : "
                        + Arrays.toString(EmploymentTypeConstants.values())));
    }
}
