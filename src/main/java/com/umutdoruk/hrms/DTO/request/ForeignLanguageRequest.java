package com.umutdoruk.hrms.DTO.request;

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
    private Long resumeId;
}
