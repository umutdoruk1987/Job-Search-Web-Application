package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatesRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
}
