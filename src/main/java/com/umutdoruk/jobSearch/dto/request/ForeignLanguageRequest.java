package com.umutdoruk.jobSearch.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguageRequest {

    private Long foreignLanguageId;
    private String languageName;
    private String languageLevel;
   /* private Long resumeId;*/
}
