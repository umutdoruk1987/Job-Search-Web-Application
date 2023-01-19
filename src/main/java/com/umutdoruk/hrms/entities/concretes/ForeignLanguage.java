package com.umutdoruk.hrms.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "foreign_languages")
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "foreign_language_id")
    private int foreignLanguageId;

    @Column(name = "language_name")
    private String languageName;

    @Column(name = "language_level")
    private String languageLevel;

    @OneToMany
    private List<Resume> resumes;
}

