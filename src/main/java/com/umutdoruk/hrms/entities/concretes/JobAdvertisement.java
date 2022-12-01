package com.umutdoruk.hrms.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_advertisements")
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_advertisement_id")
    private int jobAdvertisementId;

    @Column(name = "description")
    private String description;

    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "number_of_open_job_position")
    private int numberOfOpenJobPosition;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "active")
    private boolean active;

    @ManyToOne()
    @Column(name = "city")
    private City city;

    @ManyToOne()
    @Column(name = "job_position_id")
    private JobPosition jobPosition;

    @ManyToOne()
    @Column(name = "job_type_id")
    private JobType jobType;

    @ManyToOne()
    @Column(name = "type_of_work_id")
    private TypeOfWork typeOfWork;

    @ManyToOne()
    @Column(name = "employer_id")
    private Employer employer;


}
