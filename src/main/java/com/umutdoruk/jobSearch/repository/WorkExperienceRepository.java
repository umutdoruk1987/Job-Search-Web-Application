package com.umutdoruk.jobSearch.repository;

import com.umutdoruk.jobSearch.entities.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience,Long> {
    List<WorkExperience> findAllByOrderByEndDateDesc();

    @Query(value = "SELECT * FROM work_experiences we where we.resume_id = :resumeId", nativeQuery = true)
    List<WorkExperience> findAllByResumeId(@Param("resumeId")Long resumeId);
}
