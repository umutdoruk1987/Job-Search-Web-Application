package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
@PrimaryKeyJoinColumn(name = "user_id")
public class Candidate extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "year_of_birth")
    private LocalDate yearOfBirth;

    @Column(name = "active")
    private boolean active;

    @OneToOne(mappedBy = "candidate")
    @JoinColumn(name = "resume_id")
    private Resume resume;

}
