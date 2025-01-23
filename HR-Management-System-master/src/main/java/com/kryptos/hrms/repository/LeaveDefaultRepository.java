package com.kryptos.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.MasterLeave;

@Repository
public interface LeaveDefaultRepository extends JpaRepository<MasterLeave, Long> {
	
	

}
