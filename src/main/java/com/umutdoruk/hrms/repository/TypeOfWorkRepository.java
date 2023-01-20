package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.TypeOfWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfWorkRepository extends JpaRepository<TypeOfWork,Integer> {
}

