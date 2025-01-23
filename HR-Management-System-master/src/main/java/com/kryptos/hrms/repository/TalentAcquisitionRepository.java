package com.kryptos.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.TalentAcquisition;

@Repository
public interface TalentAcquisitionRepository extends JpaRepository<TalentAcquisition, Long> {

}
