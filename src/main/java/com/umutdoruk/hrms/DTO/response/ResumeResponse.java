package com.umutdoruk.hrms.DTO.response;

import com.umutdoruk.hrms.entities.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponse {

    private Long resumeId;
    private String coverLetter;
    private String githubUrl;
    private String linkedinUrl;
    private String imageUrl;
    private Boolean active;
    private LocalDate createdDate;
    private CandidateResponse candidate;
    private List<EducationResponse> educationList;
    private List<TechnologyResponse> technologyList;
    private List<WorkExperienceResponse> workExperienceList;
    private List<ForeignLanguageResponse> foreignLanguageList;

    public static ResumeResponse of(Resume resume,
                                    CandidateResponse candidate,
                                    List<EducationResponse> educationList,
                                    List<TechnologyResponse> technologyList,
                                    List<WorkExperienceResponse> workExperienceList,
                                    List<ForeignLanguageResponse> foreignLanguageList){
        return new ResumeResponse(resume.getId(),
                resume.getCoverLetter(),
                resume.getGithubUrl(),
                resume.getLinkedinUrl(),
                resume.getImageUrl(),
                resume.isActive(),
                resume.getCreateDate(),
                candidate,
                educationList,
                technologyList,
                workExperienceList,
                foreignLanguageList);}
}
