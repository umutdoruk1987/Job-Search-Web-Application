package com.umutdoruk.hrms.repository;

import com.umutdoruk.hrms.entities.ForeignLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForeignLanguageRepository extends JpaRepository<ForeignLanguage,Integer> {
}
