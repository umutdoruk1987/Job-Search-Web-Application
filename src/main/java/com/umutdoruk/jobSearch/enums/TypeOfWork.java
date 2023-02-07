package com.umutdoruk.jobSearch.enums;

import com.umutdoruk.jobSearch.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TypeOfWork {
    FULL_TIME,    //istihdam turu //Employment type : sinirsiz, sinirli, sezonluk,
    PART_TIME,    //
    CONTRACTOR,
    UNLIMITED,
    SEASONAL,
    TEMPORARY,
    INTERNSHIP,
    HOME_OFFICE,
    FREELANCE;

    public static TypeOfWork findByName(String typeOfWork){
        return Arrays.stream(TypeOfWork.values())
                .filter(typeOfWork1-> typeOfWork1.name().equals(typeOfWork))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Type of work is not found. Available types are : "
                        + Arrays.toString(TypeOfWork.values())));
    }
}
