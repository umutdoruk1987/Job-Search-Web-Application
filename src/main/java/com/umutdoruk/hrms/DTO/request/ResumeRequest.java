package com.umutdoruk.hrms.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeRequest {

    private String coverLetter;
    private String githubUrl;
    private String linkedinUrl;
    private String imageUrl;
    private Boolean active;
    private Long candidateId;
}
