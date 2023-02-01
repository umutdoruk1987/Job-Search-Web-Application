package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education,Long> {
    List<Education> findAllByOrderByGraduationDateAsc();
    List<Education> findAllByOrderByGraduationDateDesc();

    @Query(value = "SELECT * FROM educations e where e.resume_id = :resumeId", nativeQuery = true)
    List<Education> findAllByResumeId(@Param("resumeId")Long resumeId);
}
