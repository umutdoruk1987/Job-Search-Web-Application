package com.umutdoruk.jobSearch.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employment_type")
public class EmploymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employment_type_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(/*mappedBy = "typeOfWork"*/)
    @JoinColumn (name = "jobAdvertisement_id")
    private JobAdvertisement jobAdvertisement;

}
