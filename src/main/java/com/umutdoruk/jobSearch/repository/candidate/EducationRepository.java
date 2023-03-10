package com.umutdoruk.jobSearch.repository.candidate;

import com.umutdoruk.jobSearch.entities.candidate.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education,Long> {
    List<Education> findAllByOrderByGraduationDateAsc();
    List<Education> findAllByOrderByGraduationDateDesc();

    /*@Query(value = "SELECT * FROM educations e where e.resume_id = :resumeId", nativeQuery = true)
    List<Education> findAllByResumeId(@Param("resumeId")Long resumeId);*/

    List<Education> findAllByResumeId(Long resumeId);
}
