package com.umutdoruk.jobSearch.entities.employer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JobLocations")
public class JobLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Job_location_id")
    private Long id;

    @Column(name = "Job_location_name")
    private String jobLocationName;

    @OneToOne(/*cascade = CascadeType.ALL*//*mappedBy = "city"*/)
    @JoinColumn (name = "jobAdvertisement_id")
    private JobAdvertisement jobAdvertisement;
}