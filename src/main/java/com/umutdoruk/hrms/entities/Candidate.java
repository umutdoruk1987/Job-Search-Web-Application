package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "candidate_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "year_of_birth")
    private LocalDate yearOfBirth;

    @OneToOne(cascade = CascadeType.ALL/*mappedBy = "candidate"*/)
    @JoinColumn(name = "resume_id" )
    private Resume resume;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
