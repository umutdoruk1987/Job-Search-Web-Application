package com.umutdoruk.jobSearch.dto.response;

import com.umutdoruk.jobSearch.entities.Technology;
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
