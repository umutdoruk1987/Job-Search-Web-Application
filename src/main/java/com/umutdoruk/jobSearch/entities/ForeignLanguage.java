package com.umutdoruk.jobSearch.entities;

import com.umutdoruk.jobSearch.enums.LanguageLevelConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "language_level")
    private LanguageLevelConstants languageLevel;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}

