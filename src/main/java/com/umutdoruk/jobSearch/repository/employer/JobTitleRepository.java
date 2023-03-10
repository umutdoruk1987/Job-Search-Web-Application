package com.umutdoruk.jobSearch.repository.employer;

import com.umutdoruk.jobSearch.entities.employer.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    JobTitle findByJobAdvertisementId(Long jobAdvertisementId);
}
