package com.umutdoruk.hrms.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
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

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "active")
    private boolean active;

}
