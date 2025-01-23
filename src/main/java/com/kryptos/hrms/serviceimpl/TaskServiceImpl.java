package com.kryptos.hrms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kryptos.hrms.exception.ResourceNotFoundException;
import com.kryptos.hrms.model.Task;
import com.kryptos.hrms.model.User;
import com.kryptos.hrms.repository.TaskRepository;
import com.kryptos.hrms.repository.UserRepository;
import com.kryptos.hrms.request.TaskRequestDTO;
import com.kryptos.hrms.response.TaskResponseDTO;
import com.kryptos.hrms.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	private final TaskRepository taskRepository;

	private final UserRepository userRepository;

	public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public TaskResponseDTO addTask(TaskRequestDTO dto) {
	    Long userId = dto.getUserId();  // Get userId from the DTO

	    // Fetch the user from the repository by its ID
	    User user = userRepository.findById(userId)
	                              .orElseThrow(() -> new RuntimeException("User not found"));

		Task task = new Task();
		task.setUser(user);
		task.setTask(dto.getTask());
		task.setDescription(dto.getDescription());
		task.setDueDate(dto.getDueDate());
		
		Task save = taskRepository.save(task);
		return mapToDTO(save);
	}

	@Override
	public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
;
		task.setUser(user);
		task.setTask(dto.getTask());
		task.setDescription(dto.getDescription());
		task.setDueDate(dto.getDueDate());
		
		Task save = taskRepository.save(task);
		return mapToDTO(save);
	}

	@Override
	public List<TaskResponseDTO> getAllTaskByUserId(Long id) {
		List<Task> task = taskRepository.findTaskByUserId(id);
		if(task.isEmpty()) {
			throw new ResourceNotFoundException("No Task!!");
		}
		List<TaskResponseDTO> responseDTOs = new ArrayList<>();
		for (Task annoucements : task) {
			responseDTOs.add(mapToDTO(annoucements));
		}

		return responseDTOs;
	}
	
	public List<TaskResponseDTO> getAllTask() {
		List<Task> task = taskRepository.findAll();
		if(task.isEmpty()) {
			throw new ResourceNotFoundException("No Task!!");
		}
		List<TaskResponseDTO> responseDTOs = new ArrayList<>();
		for (Task annoucements : task) {
			responseDTOs.add(mapToDTO(annoucements));
		}

		return responseDTOs;
	}

	@Override
	public String deleteTask(Long id) {
		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
		} else {
			new ResourceNotFoundException("Task not found for ID : " + id);
		}
		return "Task deleted successfully";
	}

	private TaskResponseDTO mapToDTO(Task task) {
		TaskResponseDTO responseDTO = new TaskResponseDTO();
		responseDTO.setTaskId(task.getTaskId());
		responseDTO.setUserId(task.getUser().getId());
		responseDTO.setTask(task.getTask());
		responseDTO.setDescription(task.getDescription());
		responseDTO.setDueDate(task.getDueDate());
		responseDTO.setCreatedAt(task.getCreatedAt());
		responseDTO.setUpdatedAt(task.getUpdatedAt());
		responseDTO.setStatus(task.getStatus());

		return responseDTO;
	}



	

}
