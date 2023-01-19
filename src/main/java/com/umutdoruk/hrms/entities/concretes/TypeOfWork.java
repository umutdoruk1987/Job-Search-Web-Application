package com.umutdoruk.hrms.entities.concretes;

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
    private int typeOfWorkId;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "typeOfWork")
    private List<JobAdvertisement> jobAdvertisements;

}
