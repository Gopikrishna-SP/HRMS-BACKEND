package com.kryptos.hrms.repository;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.MasterLeave;
import com.kryptos.hrms.model.Role;
import com.kryptos.hrms.model.UpcomingBday;
import com.kryptos.hrms.model.User;

@Repository
public interface MasterLeaveRepository extends JpaRepository<MasterLeave, Long> {

	Optional<MasterLeave> findByUserId(Long id);

}
