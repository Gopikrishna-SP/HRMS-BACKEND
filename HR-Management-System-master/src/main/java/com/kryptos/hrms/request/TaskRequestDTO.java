package com.kryptos.hrms.request;

import java.time.LocalDate;

import com.kryptos.hrms.model.enums.ETaskStatus;

public class TaskRequestDTO {
	private Long userId;

	private LocalDate dueDate;

	private String task;

	private String description;

	private ETaskStatus status;

	public TaskRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public TaskRequestDTO(Long userId, LocalDate dueDate, String task, String description, ETaskStatus status) {
		super();
		this.userId = userId;
		this.dueDate = dueDate;
		this.task = task;
		this.description = description;
		this.status = status;
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

}
