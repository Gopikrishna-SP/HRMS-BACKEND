package com.kryptos.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.UpcomingBday;

@Repository
public interface UpcomingBdayRepository extends JpaRepository<UpcomingBday, Long> {

}
