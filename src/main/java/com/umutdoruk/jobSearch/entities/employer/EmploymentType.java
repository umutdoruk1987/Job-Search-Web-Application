package com.umutdoruk.jobSearch.entities.employer;

import com.umutdoruk.jobSearch.enums.EmploymentTypeConstants;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type_name")
    private EmploymentTypeConstants typeName;

    @OneToOne(/*mappedBy = "typeOfWork"*/)
    @JoinColumn (name = "jobAdvertisement_id")
    private JobAdvertisement jobAdvertisement;

}
