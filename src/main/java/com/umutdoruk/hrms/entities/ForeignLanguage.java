package com.umutdoruk.hrms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "foreign_languages")
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "foreign_language_id")
    private Long id;

    @Column(name = "language_name")
    private String languageName;

    @Column(name = "language_level")
    private String languageLevel;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}

