package com.umutdoruk.jobSearch.repository.employer;

import com.umutdoruk.jobSearch.entities.employer.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT CASE WHEN COUNT(j) > 0 THEN true ELSE false END FROM job_advertisements j inner join employers e on j.employer_id = e.employer_id " +
            "where j.job_advertisement_id = :jobAdvertisementId and e.user_id= (select user_id from users where username= :username)", nativeQuery = true)
    boolean isJobAdvertisementBelongToEmployer(@Param("jobAdvertisementId")Long jobAdvertisementId, @Param("username")String username);
}