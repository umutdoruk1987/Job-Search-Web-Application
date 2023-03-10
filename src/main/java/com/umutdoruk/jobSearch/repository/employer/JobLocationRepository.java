package com.umutdoruk.jobSearch.repository.employer;

import com.umutdoruk.jobSearch.entities.employer.JobLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobLocationRepository extends JpaRepository<JobLocation, Long> {

    /*@Query(value = "SELECT * FROM educations e where e.resume_id = :resumeId", nativeQuery = true)*/
    JobLocation findByJobAdvertisementId(Long jobAdvertisementId);
}
