package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {
    Optional<List<JobPosition>> findByName(String jobPositionName);
    JobPosition findByJobAdvertisementId(Long jobAdvertisementId);
}
