package com.kryptos.hrms.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.User;
import com.kryptos.hrms.model.UserLeave;

@Repository
public interface UserLeaveRepository extends JpaRepository<UserLeave, Long> {

	Optional<User> findByUserId(Long id);
}

