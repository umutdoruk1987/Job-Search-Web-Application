package com.umutdoruk.jobSearch.enums;

import com.umutdoruk.jobSearch.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EmploymentType {
    CONTRACTOR,
    UNLIMITED,
    SEASONAL,
    TEMPORARY;


    public static EmploymentType findByName(String employmentType){
        return Arrays.stream(EmploymentType.values())
                .filter(employmentType1 -> employmentType1.name().equals(employmentType))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Employment Type is not found. Available types are : "
                        + Arrays.toString(EmploymentType.values())));
    }
}
