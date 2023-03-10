package com.umutdoruk.jobSearch.repository.employer;

import com.umutdoruk.jobSearch.entities.employer.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Long> {
    /*Optional<List<WorkingTime>> findByName(String workingTimeName);*/
    WorkingTime findByJobAdvertisementId(Long jobAdvertisementId);
}
