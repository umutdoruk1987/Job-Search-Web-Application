package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    Employer findByEmail(String email);
}
