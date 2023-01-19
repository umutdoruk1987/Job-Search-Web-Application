package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {
    List<JobPosition> findByName(String jobPositionName);
}
