package com.umutdoruk.jobSearch.repository;

import com.umutdoruk.jobSearch.entities.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    JobTitle findByJobAdvertisementId(Long jobAdvertisementId);
}
