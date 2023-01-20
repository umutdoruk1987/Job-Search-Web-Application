package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private Long resumeId;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "resume")
    private List<Education> education;

    @OneToMany(mappedBy = "resume")
    private List<Technology> technology;

    @OneToMany(mappedBy = "resume")
    private List<WorkExperience> workExperience;

    @OneToMany(mappedBy = "resume")
    private List<ForeignLanguage> foreignLanguage;

    @OneToOne(mappedBy = "resume")
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

}