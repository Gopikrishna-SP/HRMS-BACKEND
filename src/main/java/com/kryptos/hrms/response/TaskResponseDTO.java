package com.kryptos.hrms.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kryptos.hrms.model.enums.ELeaveStatus;
import com.kryptos.hrms.model.enums.ETaskStatus;

public class TaskResponseDTO {

	private Long taskId;

	private Long userId;

	private LocalDate dueDate;

	private String task;

	private String description;

	private ETaskStatus status;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public TaskResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public TaskResponseDTO(Long taskId, Long userId, LocalDate dueDate, String task, String description,
			ETaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.taskId = taskId;
		this.userId = userId;
		this.dueDate = dueDate;
		this.task = task;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ETaskStatus getStatus() {
		return status;
	}

	public void setStatus(ETaskStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
