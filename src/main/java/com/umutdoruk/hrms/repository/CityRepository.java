package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    /*@Query(value = "SELECT * FROM educations e where e.resume_id = :resumeId", nativeQuery = true)*/
    City findByJobAdvertisementId(Long jobAdvertisementId);
}
