package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    @Id
    @Column(name = "resume_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "resume")
    private List<Education> educationList;

    @OneToMany(mappedBy = "resume")
    private List<Technology> technologyList;

    @OneToMany(mappedBy = "resume")
    private List<WorkExperience> workExperienceList;

    @OneToMany(mappedBy = "resume")
    private List<ForeignLanguage> foreignLanguageList;

    @OneToOne(cascade = CascadeType.ALL)/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}