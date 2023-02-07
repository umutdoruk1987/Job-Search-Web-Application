package com.umutdoruk.jobSearch.enums;

import com.umutdoruk.jobSearch.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LanguageLevel {
    A_1, A_2,
    B_1, B_2,
    C_1, C_2,
    MOTHER_TONGUE;

    public static LanguageLevel findByName(String languageLevel){
        return Arrays.stream(LanguageLevel.values()).filter(level-> level.name().equals(languageLevel))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Language level is not found. Available levels are : "
                        + Arrays.toString(LanguageLevel.values())));
    }

}
