package com.kryptos.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	Department findByDepartmentId(Long departmentId);

	@Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE d.departmentName = :departmentName")
	boolean existsByName(@Param("departmentName") String departmentName);


	Optional<Department> findByDepartmentName(String departmentName);

    @Query("SELECT COUNT(p) FROM Department p")
    int countAll();

}
