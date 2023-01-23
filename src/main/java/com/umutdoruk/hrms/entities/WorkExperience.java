package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "work_experiences")
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_experience_id")
    private Long id;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_position_name")
    private String jobPositionName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne()
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
