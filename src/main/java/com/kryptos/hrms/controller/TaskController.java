package com.kryptos.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.kryptos.hrms.request.TaskRequestDTO;
import com.kryptos.hrms.response.TaskResponseDTO;
import com.kryptos.hrms.response.ResponseMessage;
import com.kryptos.hrms.serviceimpl.TaskServiceImpl;
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskServiceImpl;

	@PostMapping("/add")
	@PreAuthorize("hasRole('MANAGER') or hasRole('HUMANRESOURCE') ")
	public ResponseEntity<ResponseMessage<TaskResponseDTO>> addTask(@RequestBody TaskRequestDTO dto) {
			TaskResponseDTO addTask = taskServiceImpl.addTask(dto);
			ResponseMessage<TaskResponseDTO> response = new ResponseMessage<TaskResponseDTO>(true, 1,
					"Task created successfully", addTask);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('HUMANRESOURCE') or hasRole('MANAGER') ")
	public ResponseEntity<ResponseMessage<TaskResponseDTO>> updateTask(@PathVariable Long id,
			@RequestBody TaskRequestDTO dto) {
			TaskResponseDTO updatedTask = taskServiceImpl.updateTask(id, dto);
			ResponseMessage<TaskResponseDTO> response = new ResponseMessage<TaskResponseDTO>(true, 1,
					"Task updated successfully", updatedTask);
			return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/get-all/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<List<TaskResponseDTO>>> getAllTaskById(@PathVariable Long id) {
			List<TaskResponseDTO> getAllTaskById = taskServiceImpl.getAllTaskByUserId(id);
			ResponseMessage<List<TaskResponseDTO>> response = new ResponseMessage<List<TaskResponseDTO>>(true, 1,
					"Task fetched successfully", getAllTaskById);
			return ResponseEntity.status(HttpStatus.OK).body(response);


	}

	@GetMapping("/get-all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseMessage<List<TaskResponseDTO>>> getAllTask() {
			List<TaskResponseDTO> getAllTask = taskServiceImpl.getAllTask();
			ResponseMessage<List<TaskResponseDTO>> response = new ResponseMessage<List<TaskResponseDTO>>(true, 1,
					"Task fetched successfully", getAllTask);
			return ResponseEntity.status(HttpStatus.OK).body(response);


	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('HUMANRESOURCE')or hasRole('MANAGER')  ")
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
			taskServiceImpl.deleteTask(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}