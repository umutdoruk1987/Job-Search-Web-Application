package com.umutdoruk.jobSearch.dto.response;

import com.umutdoruk.jobSearch.entities.Resume;
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
    private List<EducationResponse> educationList;
    private List<TechnologyResponse> technologyList;
    private List<WorkExperienceResponse> workExperienceList;
    private List<ForeignLanguageResponse> foreignLanguageList;

    public static ResumeResponse of(Resume resume,
                                    List<EducationResponse> educationList,
                                    List<TechnologyResponse> technologyList,
                                    List<WorkExperienceResponse> workExperienceList,
                                    List<ForeignLanguageResponse> foreignLanguageList){
         ResumeResponse resumeResponse = new ResumeResponse();
         resumeResponse.setResumeId(resume.getId());
         resumeResponse.setCoverLetter(resume.getCoverLetter());
         resumeResponse.setGithubUrl(resume.getGithubUrl());
         resumeResponse.setLinkedinUrl(resume.getLinkedinUrl());
         resumeResponse.setImageUrl(resume.getImageUrl());
         resumeResponse.setActive(resume.isActive());
         resumeResponse.setCreatedDate(resume.getCreateDate());
         /*resumeResponse.setCandidate(candidate);*/
         if (resume.getEducationList()!=null)resumeResponse.setEducationList(educationList);
         if (resume.getTechnologyList()!= null) resumeResponse.setTechnologyList(technologyList);
         if (resume.getWorkExperienceList()!=null)resumeResponse.setWorkExperienceList(workExperienceList);
         if (resume.getForeignLanguageList()!=null)resumeResponse.setForeignLanguageList(foreignLanguageList);

         return resumeResponse;
    }
}
