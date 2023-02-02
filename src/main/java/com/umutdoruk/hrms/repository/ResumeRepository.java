package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ResumeRepository extends JpaRepository<Resume,Long> {

    @Query(value = "SELECT * FROM resumes r where r.candidate_id = :candidateId", nativeQuery = true)
    Optional<Resume> findResumeByCandidateId(@Param("candidateId") Long candidateId);
}

