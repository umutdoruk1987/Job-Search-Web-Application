package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
