package com.umutdoruk.jobSearch.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyRequest {

    private Long technologyId;
    private String technologyName;
    /*private Long resumeId;*/
}
