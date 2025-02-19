package com.kryptos.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findTaskByUserId(Long id);

	

}
