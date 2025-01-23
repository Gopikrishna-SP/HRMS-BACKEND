package com.kryptos.hrms.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.Attendance;
import com.kryptos.hrms.model.User;
import com.kryptos.hrms.model.enums.EAttendanceStatus;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	@Query("SELECT a FROM Attendance a WHERE a.user.department.id = :departmentId")
	List<Attendance> findUserByDepartmentId(@Param("departmentId") Long departmentId);

	Optional<Attendance> findByUserAndStatusAndDate(User user, EAttendanceStatus string, LocalDate localDate);

	@Query("SELECT a FROM Attendance a WHERE a.user.id = :userId")
	List<Attendance> findUserByUserId(@Param("userId") Long userId);

	Optional<Attendance> findByUserAndDate(User user, LocalDate today);

	@Query("SELECT COUNT(a) FROM Attendance a WHERE a.user.id = :userId AND MONTH(a.date) = :month AND a.checkInTime IS NOT NULL")
    int countCheckedInDays(@Param("userId") Long userId, @Param("month") int month);


}
