package com.umutdoruk.jobSearch.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "working_time")
public class WorkingTime {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "working_time_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(/*mappedBy = "jobPosition"*/)
    @JoinColumn (name = "jobAdvertisement_id")
    private JobAdvertisement jobAdvertisement;

}
