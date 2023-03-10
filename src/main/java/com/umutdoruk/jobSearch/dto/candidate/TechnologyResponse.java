package com.umutdoruk.jobSearch.dto.candidate;

import com.umutdoruk.jobSearch.entities.candidate.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyResponse {

    private Long technologyId;
    private String technologyName;
    private Long resumeId;

    public static TechnologyResponse of(Technology technology){
        return new TechnologyResponse(technology.getId(),
                technology.getTechnologyName(),
                technology.getResume().getId());}
}
