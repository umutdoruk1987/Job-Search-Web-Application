package com.umutdoruk.jobSearch.repository.candidate;

import com.umutdoruk.jobSearch.entities.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidatesRepository extends JpaRepository<Candidate, Long> {
    Candidate findCandidateByUserId (Long userId);
}
