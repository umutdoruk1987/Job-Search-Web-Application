package com.umutdoruk.jobSearch.repository;

import com.umutdoruk.jobSearch.entities.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Long> {
    Optional<List<WorkingTime>> findByName(String workingTimeName);
    WorkingTime findByJobAdvertisementId(Long jobAdvertisementId);
}
