package com.kryptos.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kryptos.hrms.model.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

	List<Performance> findByUserUserId(Long id);


}
