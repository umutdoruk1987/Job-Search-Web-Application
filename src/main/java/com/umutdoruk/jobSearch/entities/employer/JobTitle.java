package com.umutdoruk.jobSearch.entities.employer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_title")
public class JobTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_title_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(/*mappedBy = "jobType"*/)
    @JoinColumn (name = "jobAdvertisement_id")
    private JobAdvertisement jobAdvertisement;

}
