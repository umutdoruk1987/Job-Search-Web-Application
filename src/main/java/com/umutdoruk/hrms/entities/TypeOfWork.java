package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "type_of_works")
public class TypeOfWork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_of_work_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "typeOfWork")
    private JobAdvertisement jobAdvertisement;

}
