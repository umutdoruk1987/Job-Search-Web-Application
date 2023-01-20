package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_positions")
public class JobPosition {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_position_id")
    private Long jobPositionId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "jobPosition")
    private List<JobAdvertisement> jobAdvertisements;

}
