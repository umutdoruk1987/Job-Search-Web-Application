package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement,Long> {
    List<JobAdvertisement> findByActiveTrue();
    List<JobAdvertisement> findByActiveTrueOrderByCreatedDateAsc();
    List<JobAdvertisement> findByActiveTrueOrderByCreatedDateDesc();
    List<JobAdvertisement> findByActiveTrueAndEmployerId(Long employerId);
    List<JobAdvertisement> findAllByEmployerId (Long employerId);

   /* @Query(value = "SELECT * FROM job_advertisements ja where ja.employer_id = :employerId", nativeQuery = true)
    List<JobAdvertisement> findAllByEmployerId (@Param("employerId")Long employerId);*/

}