package com.kryptos.hrms.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.User;
import com.kryptos.hrms.model.UserLeave;

@Repository
public interface UserLeaveRepository extends JpaRepository<UserLeave, Long> {

	@Query("SELECT ul FROM UserLeave ul WHERE ul.user.id = :userId")
    Optional<UserLeave> findByUserId(@Param("userId") Long userId);
}
