package com.kryptos.hrms.repository;

import com.kryptos.hrms.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {


    @Query("SELECT l FROM Leave l WHERE l.user.id = :id")
    List<Leave> findByUserId(@Param("id") Long id);

    @Query("SELECT l FROM Leave l WHERE l.department.departmentId = :id")
    List<Leave> findByDepartmentDepartmentId(@Param("id") Long id);

}
