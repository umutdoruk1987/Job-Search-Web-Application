package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience,Integer> {
    List<WorkExperience> findAllByOrderByEndDateDesc();
}
