package com.umutdoruk.jobSearch.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employers")
public class Employer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employer_id")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "website")
    private String website;

    @Column(name = "company_telephone_number")
    private String companyTelephoneNumber;

    /*@OneToMany(mappedBy = "employer")
    private List<JobAdvertisement> jobAdvertisements;*/

    @OneToOne(/*cascade = CascadeType.ALL*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

}
