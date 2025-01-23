package com.kryptos.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.kryptos.hrms.model.MasterPayroll;
import com.kryptos.hrms.model.enums.ERole;

@Repository
public interface PayrollDefaultRepository extends JpaRepository<MasterPayroll, Long> {
    Optional<MasterPayroll> findByRole(ERole role);
}
