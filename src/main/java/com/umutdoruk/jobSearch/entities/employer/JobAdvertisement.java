package com.umutdoruk.jobSearch.entities.employer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
