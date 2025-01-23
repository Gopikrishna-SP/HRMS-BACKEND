package com.kryptos.hrms.repository;

import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    
//    @Query("SELECT u FROM User u WHERE u.active = true")
//    List<User> findByActiveTrue();
//
//
//    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
//    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT COUNT(e) FROM User e WHERE e.department.departmentId = :departmentId")
    Long countByDepartmentId(Long departmentId);
	
    @Query("SELECT COUNT(p) FROM PersonalDetails p")
    int countAll();

    // Count employees who joined in the given month and year
    @Query("SELECT COUNT(p) FROM PersonalDetails p WHERE FUNCTION('MONTH', p.dateOfJoining) = :month AND FUNCTION('YEAR', p.dateOfJoining) = :year")
    int countByDateOfJoiningMonth(@Param("month") int month, @Param("year") int year);

    // Count employees with non-null date of exit
    @Query("SELECT COUNT(p) FROM PersonalDetails p WHERE p.dateOfExit IS NOT NULL")
    int countByDateOfExitNotNull();

    
}
