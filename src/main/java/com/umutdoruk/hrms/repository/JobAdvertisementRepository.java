package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement,Long> {
    List<JobAdvertisement> findByActiveTrue();
    List<JobAdvertisement> findByActiveTrueOrderByCreateDateAsc();
    List<JobAdvertisement> findByActiveTrueOrderByCreateDateDesc();
    List<JobAdvertisement> findByActiveTrueAndEmployerId(Long employerId);
}