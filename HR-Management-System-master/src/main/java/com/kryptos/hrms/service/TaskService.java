package com.kryptos.hrms.service;

import java.util.List;

import com.kryptos.hrms.request.TaskRequestDTO;
import com.kryptos.hrms.response.TaskResponseDTO;

public interface TaskService {

	TaskResponseDTO addTask(TaskRequestDTO dto);
	
	TaskResponseDTO updateTask(Long id, TaskRequestDTO dto);
	
	List<TaskResponseDTO> getAllTaskByUserId(Long id);
	
	List<TaskResponseDTO> getAllTask();

	
	String  deleteTask(Long id);
	
	
}
