package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "education_id")
    private int educationId;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "graduation_date")
    private Date graduationDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

}