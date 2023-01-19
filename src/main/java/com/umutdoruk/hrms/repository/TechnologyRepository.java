package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.concretes.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology,Integer> {
}
