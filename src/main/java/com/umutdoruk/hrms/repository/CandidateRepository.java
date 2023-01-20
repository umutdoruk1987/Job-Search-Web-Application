package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate findByEmail(String email);
}
