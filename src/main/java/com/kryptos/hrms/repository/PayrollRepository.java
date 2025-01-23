package com.kryptos.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.kryptos.hrms.model.Payroll;


public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    @Query("SELECT p FROM Payroll p WHERE p.user.userId = :userId")
    List<Payroll> findByUserUserId(@Param("userId") Long id);

    @Query("SELECT p FROM Payroll p WHERE p.department.departmentId = :departmentId")
    List<Payroll> findByDepartmentDepartmentId(@Param("departmentId") Long id);
}

