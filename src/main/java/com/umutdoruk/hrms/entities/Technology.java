package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "technologies")
@AllArgsConstructor
@NoArgsConstructor
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "technology_id")
    private int technologyId;

    @Column(name = "name")
    private String technologyName;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "technology")
    private List<Resume> resumes;
}

