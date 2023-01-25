package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_type")
public class JobType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_type_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "jobType")
    private JobAdvertisement jobAdvertisement;

}
