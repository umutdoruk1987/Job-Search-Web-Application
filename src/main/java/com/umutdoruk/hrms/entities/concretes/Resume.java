package com.umutdoruk.hrms.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "resumes")
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    @Id
    @Column(name = "resume_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resumeId;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "linkedin_link")
    private String linkedinLink;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "active")
    private boolean active;

    @OneToMany()
    private Education education;

    @OneToMany()
    private Technology technology;

    @OneToMany()
    private WorkExperience workExperience;

    @OneToMany()
    private ForeignLanguage foreignLanguage;

    @OneToOne()
    private Candidate candidate;

}