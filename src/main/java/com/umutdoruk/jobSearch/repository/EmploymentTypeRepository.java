package com.umutdoruk.jobSearch.repository;

import com.umutdoruk.jobSearch.entities.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentType,Long> {

    EmploymentType findByJobAdvertisementId(Long jobAdvertisementId);
}

