package com.umutdoruk.jobSearch.entities.employer;

import com.umutdoruk.jobSearch.enums.WorkingTimeConstants;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private WorkingTimeConstants name;

    @OneToOne(/*mappedBy = "jobPosition"*/)
    @JoinColumn (name = "jobAdvertisement_id")
    private JobAdvertisement jobAdvertisement;

}
