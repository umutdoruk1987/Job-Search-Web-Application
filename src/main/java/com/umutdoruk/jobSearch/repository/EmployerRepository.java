package com.umutdoruk.jobSearch.repository;

import com.umutdoruk.jobSearch.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Employer findEmployerByUserId(Long userId);
}
