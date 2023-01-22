package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepository extends JpaRepository<JobType, Long> {

}
