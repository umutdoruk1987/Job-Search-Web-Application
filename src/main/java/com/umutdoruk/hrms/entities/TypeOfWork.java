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
@Table(name = "type_of_works")
public class TypeOfWork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_of_work_id")
    private Long typeOfWorkId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "typeOfWork")
    private List<JobAdvertisement> jobAdvertisements;

}
