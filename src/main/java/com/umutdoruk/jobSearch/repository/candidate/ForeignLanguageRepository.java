package com.umutdoruk.jobSearch.repository.candidate;

import com.umutdoruk.jobSearch.entities.candidate.ForeignLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForeignLanguageRepository extends JpaRepository<ForeignLanguage,Long> {

    @Query(value = "SELECT * FROM foreign_languages fl where fl.resume_id = :resumeId", nativeQuery = true)
    List<ForeignLanguage> findAllByResumeId(@Param("resumeId")Long resumeId);
}
