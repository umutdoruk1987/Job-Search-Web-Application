package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology,Long> {

    @Query(value = "SELECT * FROM technologies t where t.resume_id = :resumeId", nativeQuery = true)
    List<Technology> findAllByResumeId(@Param("resumeId")Long resumeId);
}
