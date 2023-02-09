package com.umutdoruk.jobSearch.dto.response;

import com.umutdoruk.jobSearch.entities.ForeignLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguageResponse {

    private Long foreignLanguageId;
    private String languageName;
    private String languageLevel;
    private Long resumeId;

    public static ForeignLanguageResponse of(ForeignLanguage foreignLanguage){
        return new ForeignLanguageResponse(foreignLanguage.getId(),
                foreignLanguage.getLanguageName(),
                foreignLanguage.getLanguageLevel().name(),
                foreignLanguage.getResume().getId());
    }
}
