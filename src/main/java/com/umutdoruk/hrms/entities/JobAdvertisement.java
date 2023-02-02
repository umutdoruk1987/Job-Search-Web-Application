package com.umutdoruk.hrms.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_advertisement_id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "min_salary")
    private Long minSalary;

    @Column(name = "max_salary")
    private Long maxSalary;

    @Column(name = "number_of_open_job_position")
    private int numberOfOpenJobPosition;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "active")
    private boolean active;

    /*@OneToOne(mappedBy = "jobAdvertisement" , cascade = CascadeType.ALL)
    *//*@JoinColumn(name = "city_id")*//*
    private City city;

    @OneToOne(mappedBy = "jobAdvertisement" , cascade = CascadeType.ALL)
    *//*@JoinColumn(name = "job_position_id")*//*
    private JobPosition jobPosition;

    @OneToOne(mappedBy = "jobAdvertisement" , cascade = CascadeType.ALL)
    *//*@JoinColumn(name = "job_type_id")*//*
    private JobType jobType;

    @OneToOne(mappedBy = "jobAdvertisement" , cascade = CascadeType.ALL)
    *//*@JoinColumn(name = "type_of_work_id")*//*
    private TypeOfWork typeOfWork;
*/
    @ManyToOne()
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
